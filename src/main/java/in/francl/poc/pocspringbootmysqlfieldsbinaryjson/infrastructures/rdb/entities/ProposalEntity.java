package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities;

import com.google.gson.JsonObject;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.MapKeyJdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.JsonJdbcType;
import org.hibernate.usertype.UserType;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(
    name = "proposals",
    indexes = {
        @Index(name = "idx_proposals_person_id", columnList = "person_id"),
        @Index(name = "idx_proposals_created_at", columnList = "created_at"),
        @Index(name = "idx_proposals_updated_at", columnList = "updated_at"),
        @Index(name = "idx_proposals_metadata_account_id", columnList = "(( cast(metadata ->> '$.account_id' as char(36)) collate utf8mb4_bin ))"),
        @Index(name = "idx_proposals_process_id", columnList = "process_id"),
        @Index(name = "idx_proposals_status_id", columnList = "status_id"),
    }
)
public class ProposalEntity {

    @Id
    @Column(
        name = "id",
        unique = true,
        updatable = false,
        nullable = false,
        columnDefinition = "binary(16)"
    )
    private final UUID id;

    @Column(
        name = "created_at",
        nullable = false,
        columnDefinition = "timestamp"
    )
    private final LocalDateTime createdAt;

    @Column(
        name = "updated_at",
        nullable = true,
        columnDefinition = "timestamp default null"
    )
    private final LocalDateTime updatedAt;

    @Column(
        name = "deleted_at",
        nullable = true,
        columnDefinition = "timestamp default null"
    )
    private final LocalDateTime deletedAt;

    @Column(
        name = "metadata",
        nullable = true,
        columnDefinition = "json"
    )
    private final String metadata;

    @Column(
        name = "person_id",
        nullable = false,
        columnDefinition = "binary(16)"
    )
    private final UUID personId;

    @Column(
        name = "process_id",
        nullable = false,
        columnDefinition = "binary(16)"
    )
    private final UUID processId;

    @JoinColumn(
        name = "status_id",
        nullable = false,
        columnDefinition = "binary(16)"
    )
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private final ProposalStatusEntity status;

    @JoinColumn(
        name = "intention_id",
        nullable = false,
        columnDefinition = "binary(16)"
    )
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private final IntentionEntity intention;

    /**
     * Constructor for JPA.
     */
    protected ProposalEntity() {
        this.id = null;
        this.createdAt = null;
        this.updatedAt = null;
        this.deletedAt = null;
        this.metadata = null;
        this.personId = null;
        this.processId = null;
        this.status = null;
        this.intention = null;
    }

    private ProposalEntity(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String metadata,
        UUID personId,
        UUID processId,
        ProposalStatusEntity status,
        IntentionEntity intention
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.metadata = metadata;
        this.personId = personId;
        this.processId = processId;
        this.status = status;
        this.intention = intention;
    }

    public static ProposalEntity of(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String metadata,
        UUID personId,
        UUID processId,
        ProposalStatusEntity status,
        IntentionEntity intention
    ) {
        return new ProposalEntity(
            id,
            createdAt,
            updatedAt,
            deletedAt,
            metadata,
            personId,
            processId,
            status,
            intention
        );
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

    public ProposalStatusEntity getStatus() {
        return status;
    }

    public IntentionEntity getIntention() {
        return intention;
    }
}
