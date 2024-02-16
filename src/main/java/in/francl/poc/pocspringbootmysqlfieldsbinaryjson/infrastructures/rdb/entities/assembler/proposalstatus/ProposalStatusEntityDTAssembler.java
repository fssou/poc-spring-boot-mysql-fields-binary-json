package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalStatusEntity;

import java.util.function.Function;

public final class ProposalStatusEntityDTAssembler implements Function<ProposalStatusDT, ProposalStatusEntity> {

    @Override
    public ProposalStatusEntity apply(ProposalStatusDT context) {
        return ProposalStatusEntity.of(
            context.getId(),
            context.getName(),
            context.getDescription()
        );
    }

}
