package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors;

import java.util.List;

public interface DomainError {

    String getCode();
    String getMessage();
    String getDetails();
    List<Error> getErrors();

}
