package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Context;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public final class ContextInvalid implements ContextError {

    private final String code = "ERR-DOM-CTX-001";
    private final String message;
    private final String details;
    private final Context context;
    private final List<Error> errors;

    private ContextInvalid(String message, String details, List<Error> errors, Context context) {
        this.message = message;
        this.details = details;
        this.context = context;
        this.errors = errors;
    }

    public static ContextInvalid of(String message, String details, List<Error> errors, Context context) {
        return new ContextInvalid(message, details, errors, context);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDetails() {
        return this.details;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }

    @Override
    public Context getContext() {
        return this.context;
    }
}
