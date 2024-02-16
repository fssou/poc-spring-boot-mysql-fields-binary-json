package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainEvent;

import java.util.UUID;

public interface ContextEvent extends DomainEvent {
    UUID getId();
}
