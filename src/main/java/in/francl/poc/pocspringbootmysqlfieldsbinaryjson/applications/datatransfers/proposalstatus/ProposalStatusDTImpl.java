package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.datatransfers.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;

import java.util.UUID;

public class ProposalStatusDTImpl extends ProposalStatusWithoutIdDTImpl implements ProposalStatusDT {

    private final UUID id;

    protected ProposalStatusDTImpl(UUID id, String name, String description) {
        super(name, description);
        this.id = id;
    }

    public static ProposalStatusDT of(UUID id, String name, String description) {
        return new ProposalStatusDTImpl(id, name, description);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

}
