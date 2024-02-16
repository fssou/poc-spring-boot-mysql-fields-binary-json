package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposal.ProposalDTImpl;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalEntity;

import java.util.function.Function;

public class ProposalDTAssembler implements Function<ProposalEntity, ProposalDT> {

        @Override
        public ProposalDT apply(ProposalEntity proposalEntity) {
            return ProposalDTImpl.of(
                proposalEntity.getId(),
                proposalEntity.getCreatedAt(),
                proposalEntity.getUpdatedAt(),
                proposalEntity.getDeletedAt(),
                proposalEntity.getMetadata(),
                proposalEntity.getPersonId(),
                proposalEntity.getProcessId(),
                proposalEntity.getStatus().getId(),
                proposalEntity.getIntention().getId()
            );
        }
}
