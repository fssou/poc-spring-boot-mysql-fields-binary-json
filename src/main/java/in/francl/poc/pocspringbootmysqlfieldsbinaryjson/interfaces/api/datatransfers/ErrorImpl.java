package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers;


import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

public class ErrorImpl implements Error {

    private final String code;
    private final String message;

    public ErrorImpl(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
