package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Context;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;

public interface ContextError extends DomainError {
    Context getContext();
}
