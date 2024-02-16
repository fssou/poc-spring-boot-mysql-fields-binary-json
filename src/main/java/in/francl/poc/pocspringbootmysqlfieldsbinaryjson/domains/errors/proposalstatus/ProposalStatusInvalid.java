package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.proposalstatus;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public final class ProposalStatusInvalid implements PropostaStatusError {

    private final String code = "ERR-DOM-PPS-001";
    private final String message;
    private final String details;
    private final ProposalStatus proposalStatus;
    private final List<Error> errors;

    private ProposalStatusInvalid(String message, String details, List<Error> errors, ProposalStatus proposalStatus) {
        this.message = message;
        this.details = details;
        this.proposalStatus = proposalStatus;
        this.errors = errors;
    }

    public static ProposalStatusInvalid of(String message, String details, List<Error> errors, ProposalStatus proposalStatus) {
        return new ProposalStatusInvalid(message, details, errors, proposalStatus);
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
    public ProposalStatus getProposalStatus() {
        return this.proposalStatus;
    }
}
