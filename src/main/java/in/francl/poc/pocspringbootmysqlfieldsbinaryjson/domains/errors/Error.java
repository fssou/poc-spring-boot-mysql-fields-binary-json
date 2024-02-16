package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors;

public interface Error {

    String getCode();

    String getMessage();


    static Error of(String code, String message) {
        return ErrorImpl.of(code, message);
    }

}
