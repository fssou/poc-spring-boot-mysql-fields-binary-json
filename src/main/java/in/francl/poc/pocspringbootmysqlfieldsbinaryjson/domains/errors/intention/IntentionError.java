package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Intention;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;

public interface IntentionError extends DomainError {

    Intention getIntention();
}
