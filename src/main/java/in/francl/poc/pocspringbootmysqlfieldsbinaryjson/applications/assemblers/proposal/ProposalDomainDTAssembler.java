package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;

import java.util.function.Function;

public class ProposalDomainDTAssembler implements Function<ProposalDT, Proposal> {
    @Override
    public Proposal apply(ProposalDT proposalDT) {
        return Proposal.of(
            proposalDT.getId(),
            proposalDT.getCreatedAt(),
            proposalDT.getUpdatedAt(),
            proposalDT.getDeletedAt(),
            proposalDT.getMetadata(),
            proposalDT.getPersonId(),
            proposalDT.getProcessId(),
            proposalDT.getStatusId(),
            proposalDT.getIntentionId()
        );
    }
}
