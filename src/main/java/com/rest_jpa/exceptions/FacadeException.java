package com.rest_jpa.exceptions;

import java.util.Locale;

public class FacadeException extends RuntimeException{

    public static final String DEFAULT_RESOURCE_BUNDLE_NAME = "bundles";
    private String bundleName = DEFAULT_RESOURCE_BUNDLE_NAME;
    private String key;
    private Object[] parameters;
    @SuppressWarnings("unchecked")
    private Class bundleResolver;

    public FacadeException(String msg, String resourcekey, Object[] parameters) {
        super(msg);
        this.key = resourcekey;
        this.parameters = parameters;
    }

    public FacadeException(Throwable t, String resourcekey, Object[] parameters) {
        super(t);
        this.key = resourcekey;
        this.parameters = parameters;
    }

    public FacadeException(String msg, String resourcekey, Object[] parameters, String bundleName) {
        super(msg);
        this.key = resourcekey;
        this.parameters = parameters;
        this.bundleName = bundleName;
    }

    public FacadeException(Throwable t, String resourcekey, Object[] parameters, String bundleName) {
        super(t);
        this.key = resourcekey;
        this.parameters = parameters;
        this.bundleName = bundleName;
    }

    public FacadeException(String message, String key, Object[] parameters, Class bundleResolver) {
        this(message, key, parameters);
        setBundleResolver(bundleResolver);
    }

    protected String resolve(String message, String key, Object[] parameters) {
        return resolve(message, key, parameters, Locale.getDefault());
    }

    protected String resolve(String message, String key, Object[] parameters, Locale locale) {
        return BundleHelper.resolve(message, key, parameters, getBundleName(), getBundleResolver(), locale);
    }

    public String getLocalizedMessage() {
        return resolve(getMessage(), key, parameters);
    }

    public String getMessage() {
        String msg = super.getMessage();
        if( msg == null || msg.length() == 0 ) {
            msg = key+", "+resolve("", key, parameters);
        }
        return msg;
    }

    public String getLocalizedMessage(Locale locale) {
        return resolve(getMessage(), key, parameters, locale);
    }

    public String getKey() {
        return key;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }

    @SuppressWarnings("unchecked")
    public void setBundleResolver(Class bundleResolver){
        this.bundleResolver = bundleResolver;
    }

    @SuppressWarnings("unchecked")
    public Class getBundleResolver(){
        return this.bundleResolver;
    }
}
