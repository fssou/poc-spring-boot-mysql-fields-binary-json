package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.assemblers;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.NotFoundError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;

import java.util.function.Function;

public class NotFoundErrorAssembler implements Function<DomainError, ServiceError> {
    @Override
    public ServiceError apply(DomainError domainError) {
        return NotFoundError.of(
            domainError.getMessage(),
            domainError.getDetails()
        );
    }
}
