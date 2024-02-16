package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.contracts.Entity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.proposalstatus.ProposalStatusInvalid;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainResult;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.proposalstatus.ProposalStatusCreated;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;

import java.util.ArrayList;
import java.util.UUID;

public class ProposalStatus implements Entity<UUID> {

    private final UUID id;

    private final String name;

    private final String description;

    private ProposalStatus(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ProposalStatus of(UUID id, String name, String description) {
        return new ProposalStatus(id, name, description);
    }

    public static ProposalStatus of(String name, String description) {
        return new ProposalStatus(null, name, description);
    }

    public static Either<ProposalStatusInvalid, DomainResult<ProposalStatus, ProposalStatusCreated>> create(ProposalStatusWithoutIdDT proposalStatus) {
        var id = UUID.randomUUID();
        var name = proposalStatus.getName();
        var description = proposalStatus.getDescription();
        var proposalStatusNew = ProposalStatus.of(id, name, description);
        return proposalStatusNew.isValid().fold(
            Either::left,
            proposalStatusValid -> Either.right(
                DomainResult.of(proposalStatusValid, ProposalStatusCreated.of(proposalStatusValid.getId(), proposalStatusValid.getName()))
            )
        );
    }

    public Either<ProposalStatusInvalid, ProposalStatus> isValid() {
        var errors = new ArrayList<Error>();
        if (this.name == null || this.name.isBlank()) {
            errors.add(Error.of("field.name", "Name is required"));
        }
        if (this.description == null || this.description.isBlank()) {
            errors.add(Error.of("field.description", "Description is required"));
        }
        if (!errors.isEmpty()) {
            return Either.left(
                ProposalStatusInvalid.of(
                    "Invalid proposal status.",
                    "Cannot create a new Proposal Status with these values for this fields.",
                    errors,
                    ProposalStatus.of(this.name, this.description)
                )
            );
        }
        return Either.right(this);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
