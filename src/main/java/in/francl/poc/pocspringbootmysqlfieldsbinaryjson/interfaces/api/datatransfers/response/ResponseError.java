package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public class ResponseError extends ResponseBase {

    private final List<Error> errors;

    protected ResponseError(String code, String message, String details, List<Error> errors) {
        super(code, message, details);
        this.errors = errors;
    }

    public static ResponseError of(String code, String message, String details, List<Error> errors) {
        return new ResponseError(code, message, details, errors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
