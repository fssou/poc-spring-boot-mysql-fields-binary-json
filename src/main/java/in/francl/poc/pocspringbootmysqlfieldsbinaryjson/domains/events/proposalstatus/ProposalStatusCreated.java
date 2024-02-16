package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.proposalstatus;

import java.util.UUID;

public class ProposalStatusCreated implements ProposalStatusEvent {

    private final UUID id;
    private final String name;

    private ProposalStatusCreated(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProposalStatusCreated of(UUID id, String name) {
        return new ProposalStatusCreated(id, name);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
