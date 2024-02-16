package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.proposal;

import java.util.UUID;

public class ProposalCreated implements ProposalEvent {

    private final UUID id;
    private final UUID intentionId;

    private ProposalCreated(UUID id, UUID intentionId) {
        this.id = id;
        this.intentionId = intentionId;
    }

    public static ProposalCreated of(UUID id, UUID intentionId) {
        return new ProposalCreated(id, intentionId);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public UUID getIntentionId() {
        return this.intentionId;
    }

}
