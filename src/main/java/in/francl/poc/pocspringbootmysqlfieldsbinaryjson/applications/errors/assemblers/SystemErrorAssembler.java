package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.assemblers;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.SystemError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;

import java.util.function.Function;

public class SystemErrorAssembler implements Function<Try<?>, ServiceError> {
    @Override
    public ServiceError apply(Try<?> aTry) {
        return SystemError.of(aTry.getException());
    }
}
