package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.contracts.Entity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.proposal.ProposalInvalid;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainResult;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.proposal.ProposalCreated;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Proposal implements Entity<UUID> {

    private final UUID id;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private final LocalDateTime deletedAt;

    private final String metadata;

    private final UUID personId;

    private final UUID processId;

    private final UUID statusId;

    private final UUID intentionId;

    private Proposal(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String metadata,
        UUID personId,
        UUID processId,
        UUID statusId,
        UUID intentionId
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.metadata = metadata;
        this.personId = personId;
        this.processId = processId;
        this.statusId = statusId;
        this.intentionId = intentionId;
    }

    public static Proposal of(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String metadata,
        UUID personId,
        UUID processId,
        UUID statusId,
        UUID intentionId
    ) {
        return new Proposal(
            id,
            createdAt,
            updatedAt,
            deletedAt,
            metadata,
            personId,
            processId,
            statusId,
            intentionId
        );
    }

    public static Either<ProposalInvalid, DomainResult<Proposal, ProposalCreated>> create(ProposalWithoutIdDT proposalWithoutIdDT) {
        var id = UUID.randomUUID();
        var createdAt = LocalDateTime.now();
        var updatedAt = LocalDateTime.now();
        LocalDateTime deletedAt = null;
        var metadata = proposalWithoutIdDT.getMetadata();
        var personId = proposalWithoutIdDT.getPersonId();
        var processId = proposalWithoutIdDT.getProcessId();
        var statusId = proposalWithoutIdDT.getStatusId();
        var intentionId = proposalWithoutIdDT.getIntentionId();

        var proposal = Proposal.of(
            id,
            createdAt,
            updatedAt,
            deletedAt,
            metadata,
            personId,
            processId,
            statusId,
            intentionId
        );

        return proposal.isValid().fold(
            Either::left,
            proposalValid -> Either.right(
                DomainResult.of(proposalValid, ProposalCreated.of(proposalValid.getId(), proposalValid.getIntentionId()))
            )
        );
    }

    public Either<ProposalInvalid, Proposal> isValid() {
        var errors = new ArrayList<Error>();
        if (personId == null) {
            errors.add(Error.of("field.person", "Person ID is required"));
        }
        if (processId == null) {
            errors.add(Error.of("field.process", "Process ID is required"));
        }
        if (statusId == null) {
            errors.add(Error.of("field.status", "Status ID is required"));
        }
        if (intentionId == null) {
            errors.add(Error.of("field.intention", "Intention ID is required"));
        }
        if (!errors.isEmpty()) {
            return Either.left(
                ProposalInvalid.of(
                    "Invalid proposal.",
                    "Cannot create a new Proposal with these values for this fields.",
                    errors,
                    this
                )
            );
        }

        return Either.right(this);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public String getMetadata() {
        return metadata;
    }

    public UUID getPersonId() {
        return personId;
    }

    public UUID getProcessId() {
        return processId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public UUID getIntentionId() {
        return intentionId;
    }
}
