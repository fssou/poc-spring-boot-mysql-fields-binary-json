package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalWithoutIdDT;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonPropertyOrder
public class ProposalDTRequestWithoutId implements ProposalWithoutIdDT {

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Map<String, Object> metadata;
    private final UUID personId;
    private final UUID processId;
    private final UUID statusId;
    private final UUID intentionId;

    private ProposalDTRequestWithoutId(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Map<String, Object> metadata,
        UUID personId,
        UUID processId,
        UUID statusId,
        UUID intentionId
    ) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.metadata = metadata;
        this.personId = personId;
        this.processId = processId;
        this.statusId = statusId;
        this.intentionId = intentionId;
    }

    @JsonCreator
    public static ProposalDTRequestWithoutId of(
        @JsonProperty(value = "metadata") Map<String, Object> metadata,
        @JsonProperty(value = "personId") UUID personId,
        @JsonProperty(value = "processId") UUID processId,
        @JsonProperty(value = "statusId") UUID statusId,
        @JsonProperty(value = "intentionId") UUID intentionId
    ) {
        return new ProposalDTRequestWithoutId(null, null, null, metadata, personId, processId, statusId, intentionId);
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
    public Map<String, Object> getMetadata() {
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
