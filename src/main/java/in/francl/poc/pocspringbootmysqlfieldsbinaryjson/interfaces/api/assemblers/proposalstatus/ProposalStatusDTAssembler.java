package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposalstatus.ProposalStatusDTImpl;

import java.util.function.Function;

public class ProposalStatusDTAssembler implements Function<ProposalStatus, ProposalStatusDT> {
    @Override
    public ProposalStatusDT apply(ProposalStatus proposalStatus) {
        return ProposalStatusDTImpl.of(
            proposalStatus.getId(),
            proposalStatus.getName(),
            proposalStatus.getDescription()
        );
    }
}
