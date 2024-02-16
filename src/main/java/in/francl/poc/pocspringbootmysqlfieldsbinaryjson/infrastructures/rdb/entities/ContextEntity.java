package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Table(
    name = "contexts",
    indexes = {
        @Index(name = "idx_contexts_name", columnList = "name")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_contexts_name", columnNames = "name")
    }
)
public class ContextEntity implements Persistable<UUID> {

    @Id
    @Column(
        name = "id",
        unique = true,
        updatable = false,
        nullable = false,
        columnDefinition = "binary(16)"
    )
    private UUID id;

    @Column(
        name = "name",
        nullable = false,
        unique = true,
        length = 256
    )
    private String name;

    @Column(
        name = "description",
        nullable = false,
        length = 512
    )
    private String description;

    protected ContextEntity() {}

    protected ContextEntity(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected ContextEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ContextEntity of(UUID id, String name, String description) {
        return new ContextEntity(id, name, description);
    }

    public static ContextEntity of(String name, String description) {
        return new ContextEntity(name, description);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isNew() {
        return false;
    }

}
