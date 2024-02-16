package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposalstatus.ProposalStatusDTImpl;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalStatusEntity;

import java.util.function.Function;

public class ProposalStatusDTAssembler implements Function<ProposalStatusEntity, ProposalStatusDT> {

    @Override
    public ProposalStatusDT apply(ProposalStatusEntity context) {
        return ProposalStatusDTImpl.of(
            context.getId(),
            context.getName(),
            context.getDescription()
        );
    }

}
