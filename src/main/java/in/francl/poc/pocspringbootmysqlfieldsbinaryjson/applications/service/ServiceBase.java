package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.assemblers.ApplicationErrorAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.assemblers.SystemErrorAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;

import java.util.Optional;
import java.util.UUID;

abstract class ServiceBase {

    protected ServiceError asServiceError(DomainError domainError) {
        return Transformer.transform(domainError, ApplicationErrorAssembler::new);
    }

    protected ServiceError asServiceError(Try<?> infraError) {
        return Transformer.transform(infraError, SystemErrorAssembler::new);
    }

    protected ServiceError asServiceError(Optional<Try<?>> infraError) {
        return Transformer.transform(infraError.get(), SystemErrorAssembler::new);
    }

    protected UUID asUUID(String id) {
        return UUID.fromString(id);
    }
}
