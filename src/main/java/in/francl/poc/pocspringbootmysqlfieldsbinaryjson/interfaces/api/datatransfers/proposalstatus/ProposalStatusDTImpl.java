package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;

import java.util.UUID;

public class ProposalStatusDTImpl implements ProposalStatusDT {

    private final UUID id;
    private final String name;
    private final String description;

    private ProposalStatusDTImpl(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ProposalStatusDTImpl of(
        UUID id,
        String name,
        String description
    ) {
        return new ProposalStatusDTImpl(id, name, description);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
