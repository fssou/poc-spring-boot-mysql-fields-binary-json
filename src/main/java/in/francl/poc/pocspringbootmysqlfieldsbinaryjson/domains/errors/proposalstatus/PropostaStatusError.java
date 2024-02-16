package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;

public interface PropostaStatusError extends DomainError {
    ProposalStatus getProposalStatus();
}
