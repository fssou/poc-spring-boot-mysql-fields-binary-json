package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public class ProposalInvalid implements ProposalError {

    private final String code = "ERR-DOM-PPS-001";
    private final String message;
    private final String details;
    private final Proposal proposal;
    private final List<Error> errors;

    private ProposalInvalid(String message, String details, List<Error> errors, Proposal proposal) {
        this.message = message;
        this.details = details;
        this.proposal = proposal;
        this.errors = errors;
    }

    public static ProposalInvalid of(String message, String details, List<Error> errors, Proposal proposal) {
        return new ProposalInvalid(message, details, errors, proposal);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDetails() {
        return this.details;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }

    @Override
    public Proposal getProposal() {
        return this.proposal;
    }
}
