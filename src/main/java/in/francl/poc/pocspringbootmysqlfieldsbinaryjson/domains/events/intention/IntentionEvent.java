package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainEvent;

import java.util.UUID;

public interface IntentionEvent extends DomainEvent {
    UUID getId();
}
