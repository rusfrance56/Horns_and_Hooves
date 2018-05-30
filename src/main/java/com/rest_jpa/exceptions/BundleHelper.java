package com.rest_jpa.exceptions;

import java.util.IllegalFormatException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BundleHelper {
	private static final Logger log = Logger.getLogger(BundleHelper.class.getName());

    public static final Locale LOCALE_RU = new Locale("ru");

	@SuppressWarnings("unchecked")
	public static final String resolve(Class resolver, String key){
		String bundleName = "Bundle";
		if( resolver == null ) {
			return "";
		}
		Package pack = resolver.getPackage();
		if( pack != null ) {
			bundleName = pack.getName() + ".Bundle";
		}
		return resolve(resolver, bundleName, key, new String[0], Locale.getDefault());
	}
	
	@SuppressWarnings("unchecked")
	public static final String resolve(Class resolver, String key, Locale locale){
		String bundleName = "Bundle";
		if( resolver == null ) {
			return "";
		}
		Package pack = resolver.getPackage();
		if( pack != null ) {
			bundleName = pack.getName() + ".Bundle";
		}
		return resolve(resolver, bundleName, key, new String[0], locale);
	}
	
	@SuppressWarnings("unchecked")
	public static final String resolve(Class resolver, String bundleName, String key, Object[] params, Locale locale){
        // assertion
        if (key == null) {
            return key;
        }
        if (resolver == null) {
        	return "";
        }

        ResourceBundle bundle;
        String formatString;
        String s = "";
        
        try {
            bundle = ResourceBundle.getBundle(bundleName, locale, resolver.getClassLoader());
            // resolving key
            s = bundle.getString(key);
            formatString = String.format(s, params);
            return formatString;
        }
        catch (MissingResourceException ex) {
        	log.severe("MissingResourceException: Key=" + key + " not found in bundle=" + bundleName + ", Resolver=" + resolver.getCanonicalName() + ", " + ex.getMessage());
            return key;
        }
        catch (IllegalFormatException ex) {
        	log.severe("IllegalFormatException: Value=" + s + ", " + ex.getMessage());
            return key;
        }
	}

    public static final String resolve(String message, String key, Object[] parameters, String bundleName, Class bundleResolver, Locale locale) {

        if (key == null) {
            return message;
        }

        ResourceBundle bundle;
        String formatString;
        String s= "";
        try {
            // check for bundle
            if (bundleResolver == null){
                bundle = ResourceBundle.getBundle(bundleName, locale);
            } else{
                bundle = ResourceBundle.getBundle(bundleName, locale,bundleResolver.getClassLoader());
            }
            // resolving key
            s = bundle.getString(key);
            formatString = String.format(s, parameters);
            return formatString;
        }
        catch (MissingResourceException ex) {
            log.info("Key or bundle not found: Key=" + key + ", Bundle=" + bundleName);
            return message;
        }
        catch (IllegalFormatException ife){
            log.severe("IllegalFormatException: Value=" + s);
            return "--- CONVERSION EXCEPTION >>>>> "+key;
        }
    }

	public static Locale getLocale(String localString) throws IllegalArgumentException{
		Locale ret;
		
		String[] locstr = localString.split("_");
		switch(locstr.length){
			case 1: ret = new Locale(locstr[0]); break;
			case 2: ret = new Locale(locstr[0], locstr[1]); break;
			case 3: ret = new Locale(locstr[0], locstr[1], locstr[2]); break;
			default: throw new IllegalArgumentException("Could not parse locale from string: " + localString);
		}
		return ret;
	}
}
