package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.DomainError;

public interface ProposalError extends DomainError {

    Proposal getProposal();
}
