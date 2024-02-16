package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProposalDTImpl implements ProposalDT {

    private final UUID id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final String metadata;
    private final UUID personId;
    private final UUID processId;
    private final UUID statusId;
    private final UUID intentionId;

    private ProposalDTImpl(
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

    public static ProposalDTImpl of(
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
        return new ProposalDTImpl(id, createdAt, updatedAt, deletedAt, metadata, personId, processId, statusId, intentionId);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    @Override
    public String getMetadata() {
        return metadata;
    }

    @Override
    public UUID getPersonId() {
        return personId;
    }

    @Override
    public UUID getProcessId() {
        return processId;
    }

    @Override
    public UUID getStatusId() {
        return statusId;
    }

    @Override
    public UUID getIntentionId() {
        return intentionId;
    }
}
