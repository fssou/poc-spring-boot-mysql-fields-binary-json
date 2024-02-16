package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposal.ProposalDTImpl;

import java.util.function.Function;

public class ProposalDTAssembler implements Function<Proposal, ProposalDT> {
    @Override
    public ProposalDT apply(Proposal proposal) {
        return ProposalDTImpl.of(
            proposal.getId(),
            proposal.getCreatedAt(),
            proposal.getUpdatedAt(),
            proposal.getDeletedAt(),
            proposal.getMetadata(),
            proposal.getPersonId(),
            proposal.getProcessId(),
            proposal.getStatusId(),
            proposal.getIntentionId()
        );
    }
}
