package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.IntentionEntity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalEntity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalStatusEntity;

import java.util.function.Function;

public class ProposalEntityDTAssembler implements Function<ProposalDT, ProposalEntity> {

        @Override
        public ProposalEntity apply(ProposalDT proposalDT) {
            return ProposalEntity.of(
                proposalDT.getId(),
                proposalDT.getCreatedAt(),
                proposalDT.getUpdatedAt(),
                proposalDT.getDeletedAt(),
                proposalDT.getMetadata(),
                proposalDT.getPersonId(),
                proposalDT.getProcessId(),
                ProposalStatusEntity.of(proposalDT.getStatusId(), null, null),
                IntentionEntity.of(proposalDT.getIntentionId(), null, null, null)
            );
        }
}
