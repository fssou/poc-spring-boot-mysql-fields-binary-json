package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposalstatus;


import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusWithoutIdDT;

public class ProposalStatusWithoutIdDTImpl implements ProposalStatusWithoutIdDT {

    private final String name;
    private final String description;

    protected ProposalStatusWithoutIdDTImpl(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ProposalStatusWithoutIdDTImpl of(String name, String description) {
        return new ProposalStatusWithoutIdDTImpl(name, description);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
