package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Intention;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public class IntentionInvalid implements IntentionError {

    private final String code = "ERR-DOM-ITT-001";
    private final String message;
    private final String details;
    private final Intention intention;
    private final List<Error> errors;

    private IntentionInvalid(String message, String details, List<Error> errors, Intention intention) {
        this.message = message;
        this.details = details;
        this.intention = intention;
        this.errors = errors;
    }

    public static IntentionInvalid of(String message, String details, List<Error> errors, Intention intention) {
        return new IntentionInvalid(message, details, errors, intention);
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
    public Intention getIntention() {
        return this.intention;
    }
}
