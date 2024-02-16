package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors;

class ErrorImpl implements Error {

    private final String code;
    private final String message;
    private ErrorImpl(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error of(String code, String message) {
        return new ErrorImpl(code, message);
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
