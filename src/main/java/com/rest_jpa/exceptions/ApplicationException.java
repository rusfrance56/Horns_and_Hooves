package com.rest_jpa.exceptions;

import org.apache.logging.log4j.util.Strings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public class ApplicationException extends FacadeException{
    private static final long serialVersionUID = 1L;
    private static final String resourceBundle = "bundles/Bundle";
    private ErrorKey key;

    public ApplicationException(ErrorKey key, Object... parameters) {
        super("ApplicationException." + key.name() + ": " + Arrays.toString(parameters), key.name(), parameters, resourceBundle);
        this.key = key;
        setBundleResolver(ProjectBundleResolver.class);
    }

    public ApplicationException(ErrorKey key, String param1) {
        super("ApplicationException." + key.name() + ":" + param1, key.name(), new Object[]{param1}, resourceBundle);
        this.key = key;
        setBundleResolver(ProjectBundleResolver.class);
    }

    public ApplicationException(ErrorKey key) {
        super("ApplicationException." + key.name(), key.name(), new Object[]{}, resourceBundle);
        this.key = key;
        setBundleResolver(ProjectBundleResolver.class);
    }

    public ApplicationException(Throwable t, ErrorKey key, Object... parameters) {
        super(t, key.name(), parameters, resourceBundle);
        this.key = key;
        setBundleResolver(ProjectBundleResolver.class);
    }

    public ApplicationException(Throwable t, ErrorKey key, String param1) {
        super(t, key.name(), new Object[]{param1}, resourceBundle);
        this.key = key;
        setBundleResolver(ProjectBundleResolver.class);
    }

    public ApplicationException(Throwable t, ErrorKey key) {
        super(t, key.name(), new Object[]{}, resourceBundle);
        this.key = key;
        setBundleResolver(ProjectBundleResolver.class);
    }

    public ErrorKey getErrorKey() {
        return key;
    }




    public static void checkArgument(boolean condition, ErrorKey key, Object... arguments) throws ApplicationException {
        if (!condition) {
            throw new ApplicationException(key, arguments);
        }
    }

    public static void checkArgument(boolean condition, ErrorKey key, Supplier<String>... argumentsSuppliers) throws ApplicationException {
        if (!condition) {
            throw new ApplicationException(key,
                    Arrays.stream(argumentsSuppliers)
                            .map(Supplier::get)
                            .toArray());
        }
    }

    @Nonnull
    public static <T> Optional<T> checkIsEmpty(@Nonnull Optional<T> value, ErrorKey key, Supplier<String>... argumentsSuppliers) throws ApplicationException {
        if (value.isPresent()) {
            throw new ApplicationException(key,
                    Arrays.stream(argumentsSuppliers)
                            .map(Supplier::get)
                            .toArray());
        } else {
            return value;
        }
    }

    @Nonnull
    public static <T> T checkNotNull(@Nullable T value, ErrorKey key, Object... arguments) throws ApplicationException {
        if (value == null) {
            throw new ApplicationException(key, arguments);
        } else {
            return value;
        }
    }

    @Nullable
    public static <T> T checkIsNull(@Nullable T value, ErrorKey key, Object... arguments) throws ApplicationException {
        if (value != null) {
            throw new ApplicationException(key, arguments);
        } else {
            return value;
        }
    }

    public static String checkNotNullAndNotEmpty(String value, ErrorKey key, Object... arguments) throws ApplicationException {
        if (Strings.isBlank(value)) {
            throw new ApplicationException(key, arguments);
        } else {
            return value;
        }
    }

    public static <C extends Collection<E>, E> C checkNotNullAndNotEmpty(C value, ErrorKey key, Object... arguments) throws ApplicationException {
        if (value == null || value.isEmpty()) {
            throw new ApplicationException(key, arguments);
        } else {
            return value;
        }
    }

    public static FacadeException reThrow(FacadeException e) throws FacadeException {
        throw new FacadeException(e, e.getKey(), e.getParameters(), e.getBundleName());
    }

    public static Supplier<? extends ApplicationException> ApplicationException(ErrorKey key, Object... arguments) {
        return () -> new ApplicationException(key, arguments);
    }
}
