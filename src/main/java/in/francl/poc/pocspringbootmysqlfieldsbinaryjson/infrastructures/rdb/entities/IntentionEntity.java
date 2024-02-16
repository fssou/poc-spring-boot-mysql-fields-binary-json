package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "intentions",
    indexes = {
        @Index(name = "idx_intentions_name", columnList = "name")
    }
)
public class IntentionEntity {

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
        unique = true,
        length = 256
    )
    private final String name;

    @Column(
        name = "description",
        nullable = false,
        length = 512
    )
    private final String description;

    @JoinColumn(
        name = "context_id",
        nullable = false,
        columnDefinition = "binary(16)"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private final ContextEntity context;

    /**
     * Constructor for JPA
     */
    protected IntentionEntity() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.context = null;
    }

    private IntentionEntity(UUID id, String name, String description, ContextEntity context) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.context = context;
    }

    public static IntentionEntity of(UUID id, String name, String description, ContextEntity context) {
        return new IntentionEntity(id, name, description, context);
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

    public ContextEntity getContext() {
        return context;
    }

}
