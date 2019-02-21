import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ChangeSetIncludesTest {
    private static final Logger LOG = LoggerFactory.getLogger("INCLUDE-TEST");

    public static final Pattern PATTERN= Pattern.compile("db\\.changelog-(\\d.*)\\.xml");
    public static final Pattern VERSION_PATTERN= Pattern.compile(".*(db\\.changelog-(.*)\\.xml)");
    public static final String MAIN_CHANGESET = "com/horns_and_hooves/db/changelog/changelog-master.xml";
    public static final Set<String> EXCLUDED = ImmutableSet.of(



    );

    @Test
    public void testAllIncludeProperly() throws Exception {

        final String prefixDir = System.getProperty("target_dir");
        if(prefixDir==null){
            throw new IllegalStateException("Cant run test! you dont set target_dir property or run not from maven. see pom.xml");
        }
        System.out.println(prefixDir);
        final  List<String> sortedVersionFiles = new ArrayList<>(new Reflections("com.horns_and_hooves.db.changelog",new ResourcesScanner())
                .getResources(PATTERN));
        Collections.sort(sortedVersionFiles,new VersionComparator());
        for(String f : sortedVersionFiles){
            checkAllfilesIncludedToChangeset(prefixDir, f);
        }
        checkAllIncludedInRootChangeSet(prefixDir, sortedVersionFiles);
    }

    private void checkAllIncludedInRootChangeSet(String prefixDir, List<String> sorted) throws URISyntaxException, ParserConfigurationException, IOException, SAXException {
        File rootChangeset = new File(getClass().getClassLoader().getResource(MAIN_CHANGESET).toURI());
        Path parentPath = Paths.get(prefixDir).relativize(rootChangeset.toPath());
        Document doc = getDocument(rootChangeset);
        Set<String> included = getIncludedFiles(doc,prefixDir,false);
        sorted.remove(MAIN_CHANGESET);
        Set<String> notIncluded = Sets.difference(new HashSet<>(sorted), included);
        Set<String> includedButNotExists = Sets.difference(included,new HashSet<>(sorted));
        check(parentPath,notIncluded,includedButNotExists);

    }
    private void check(Path parentPath, Set<String> notIncludedfles, Set<String> includedButNotExists) {
        System.out.println("\n################################################################################\n");
        notIncludedfles = Sets.difference(notIncludedfles,EXCLUDED);
        includedButNotExists = Sets.difference(includedButNotExists,EXCLUDED);
        if(!notIncludedfles.isEmpty()){
            System.out.println("Found not included files :  " + Joiner.on(";\t").join(notIncludedfles));
            System.out.println("Please include this files in " + parentPath.toString());

        }
        if(!includedButNotExists.isEmpty()){
            System.out.println("Found included files but not exists : " + Joiner.on(";\t").join(includedButNotExists));
            System.out.println("Please create this files or remove include from  " + parentPath.toString());

        }
        assertTrue("Found files not included in " + parentPath.toString(),notIncludedfles.isEmpty());
        assertTrue("Found files  included in " + parentPath.toString()+" but not exists " ,includedButNotExists.isEmpty());
        System.out.println("All files properly included to " + parentPath.toString());
    }

    private void checkAllfilesIncludedToChangeset(String prefixDir, String f) throws URISyntaxException, ParserConfigurationException, SAXException, IOException {
        File versionChangeSetFile = new File(getClass().getClassLoader().getResource(f).toURI());
        checkAllfilesIncuded(versionChangeSetFile.getParentFile(), getDocument(versionChangeSetFile), prefixDir);
    }

    private void checkAllfilesIncuded(File parentFile, Document changeLogXml, String prefixDir) throws ParserConfigurationException, SAXException, IOException {
        if(!parentFile.isDirectory()){
            throw new IllegalStateException(parentFile.getAbsolutePath()+"is not directory");
        }
        Path parentPath = Paths.get(prefixDir).relativize(parentFile.toPath());
        Set<String> realFiles = Sets.newHashSet();
        for (File f : Files.fileTreeTraverser().preOrderTraversal(parentFile).
                filter(Files.isFile()).
                filter(new Predicate<File>() {
                            @Override
                            public boolean apply(File input) {//find al xml files exclude db-changelog-{version}.xml
                    return "xml".equals(Files.getFileExtension(input.getName()))
                            && !PATTERN.matcher(input.getName()).matches();
                }
                            }))
        {
            Path relativePath = Paths.get(prefixDir).relativize(f.toPath());
            realFiles.add(relativePath.toString());
        }
      Set<String> includedFiles = getIncludedFiles(changeLogXml,prefixDir,true);

      Set<String> notIncludedfles = Sets.difference(realFiles,includedFiles);
      Set<String> includedButNotExists = Sets.difference(includedFiles,realFiles);
      check(parentPath, notIncludedfles, includedButNotExists);

    }

    private Set<String> getIncludedFiles(Document changeLogXml,String parentFile,boolean recursive) throws IOException, SAXException, ParserConfigurationException {
        Set<String> result = Sets.newHashSet();
        NodeList nodelist =changeLogXml.getElementsByTagName("include");
        for(int i=0;i<nodelist.getLength();i++){
            Node fileAttr =  nodelist.item(i).getAttributes().getNamedItem("file");
            if(fileAttr!=null){
                String filename=fileAttr.getNodeValue();
                File f =Paths.get(parentFile,filename).toFile();
                result.add(fileAttr.getNodeValue());
                if(f.exists() && recursive){
                    Document document =getDocument(f);
                    Set<String> included = getIncludedFiles(document,parentFile,recursive);
                    result.addAll(included);
                }
            }
        }
        return result;
    }

    private Document getDocument(File f) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(f);
    }

    private static class VersionComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            Matcher v1 = VERSION_PATTERN.matcher(o1);
            Matcher v2 = VERSION_PATTERN.matcher(o2);
            String vers1=null;
            String vers2=null;
            if(v1.matches()){
                vers1 = v1.group(2);
            }
            if(v2.matches()){
                vers2 = v2.group(2);

            }
            //both null case
            if(vers1==vers2){
                return 0;
            }
            // one of null
            if(vers1!=null ^ vers2!=null){
              return  vers1==null?-1:1;
            }
            // both different not null
            return vers1.compareTo(vers2);
        }
    }
}
