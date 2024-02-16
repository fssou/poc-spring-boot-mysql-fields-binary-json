package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.assemblers;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ApplicationError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;

import java.util.function.Function;

public class ApplicationErrorAssembler implements Function<DomainError, ServiceError> {
    @Override
    public ServiceError apply(DomainError domainError) {
        return ApplicationError.of(
            domainError.getCode(),
            domainError.getMessage(),
            domainError.getDetails(),
            domainError.getErrors()
        );
    }
}
