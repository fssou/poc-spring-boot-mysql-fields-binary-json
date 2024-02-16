package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public sealed abstract class ServiceError permits ApplicationError, SystemError, NotFoundError {

    private final String code;
    private final String message;
    private final String details;
    private final List<Error> errors;

    protected ServiceError(String code, String message, String details, List<Error> errors) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public List<Error> getErrors() {
        return errors;
    }

}
