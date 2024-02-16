package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "proposal_statuses",
    indexes = {
        @Index(name = "idx_proposal_statuses_name", columnList = "name")
    }
)
public class ProposalStatusEntity {

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
        name = "name",
        nullable = false,
        columnDefinition = "varchar(255)"
    )
    private final String name;

    @Column(
        name = "description",
        nullable = false,
        columnDefinition = "varchar(512)"
    )
    private final String description;

    /**
     * Constructor for JPA
     */
    protected ProposalStatusEntity() {
        this.id = null;
        this.name = null;
        this.description = null;
    }

    private ProposalStatusEntity(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ProposalStatusEntity of(UUID id, String name, String description) {
        return new ProposalStatusEntity(id, name, description);
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
