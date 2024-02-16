package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public non-sealed class ApplicationError extends ServiceError {

    protected ApplicationError(String code, String message, String details, List<Error> errors) {
        super(code, message, details, errors);
    }

    public static ApplicationError of(String code, String message, String details, List<Error> errors) {
        return new ApplicationError(code, message, details, errors);
    }

}
