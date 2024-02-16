package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainEvent;

import java.util.UUID;

public interface ProposalStatusEvent extends DomainEvent {
    UUID getId();
}
