package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public non-sealed class NotFoundError extends ServiceError {

    protected NotFoundError(String code, String message, String details, List<Error> errors) {
        super(code, message, details, errors);
    }

    public static NotFoundError of(String message, String details) {
        return new NotFoundError("error.not_found", message, details, null);
    }

}
