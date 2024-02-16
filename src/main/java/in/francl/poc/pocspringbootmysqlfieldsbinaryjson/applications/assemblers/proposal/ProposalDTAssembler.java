package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposal.ProposalDTImpl;

import java.util.function.Function;

public class ProposalDTAssembler implements Function<Proposal, ProposalDT> {

        @Override
        public ProposalDT apply(Proposal proposalEntity) {
            return ProposalDTImpl.of(
                proposalEntity.getId(),
                proposalEntity.getCreatedAt(),
                proposalEntity.getUpdatedAt(),
                proposalEntity.getDeletedAt(),
                proposalEntity.getMetadata(),
                proposalEntity.getPersonId(),
                proposalEntity.getProcessId(),
                proposalEntity.getStatusId(),
                proposalEntity.getIntentionId()
            );
        }

}
