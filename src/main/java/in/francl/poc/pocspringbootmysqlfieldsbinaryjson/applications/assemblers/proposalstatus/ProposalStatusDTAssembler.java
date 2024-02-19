package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposalstatus.ProposalStatusDTImpl;

import java.util.function.Function;

public class ProposalStatusDTAssembler implements Function<ProposalStatus, ProposalStatusDT> {

    @Override
    public ProposalStatusDT apply(ProposalStatus context) {
        return ProposalStatusDTImpl.of(
            context.getId(),
            context.getName(),
            context.getDescription()
        );
    }

}
