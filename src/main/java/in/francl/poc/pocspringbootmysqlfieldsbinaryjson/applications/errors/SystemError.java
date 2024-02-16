package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors;

import java.util.Collections;

public non-sealed class SystemError extends ServiceError {

    public SystemError(Throwable cause) {
        super("error.system", cause.getMessage(), cause.getCause().getMessage(), Collections.emptyList());
    }

    public static SystemError of(Throwable cause) {
        return new SystemError(cause);
    }
}
