package com.rest_jpa.fileUploading;

import com.rest_jpa.config.ApplicationProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public FileSystemStorageService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.rootLocation = Paths.get(applicationProperties.getUploadPath());
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String uuid = UUID.randomUUID().toString();
            String finalFileName = uuid + "." + file.getOriginalFilename();
            Path destinationFile = this.rootLocation.resolve(Paths.get(finalFileName)).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return finalFileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path path = load(filename);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (FileNotFoundException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(String fileName) {
        boolean result = false;
        if (Strings.isBlank(fileName)) {
            return false;
        }
        try {
            Path path = load(fileName);
            result = Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
