package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainEvent;

import java.util.UUID;

public interface ProposalEvent extends DomainEvent {
    UUID getId();
}
