package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.contracts.Entity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.context.ContextInvalid;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainResult;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.context.ContextCreated;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;

import java.util.ArrayList;
import java.util.UUID;

public class Context implements Entity<UUID> {

    private final UUID id;
    private final String name;
    private final String description;


    protected Context(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Either<ContextInvalid, DomainResult<Context, ContextCreated>> create(ContextWithoutIdDT contextWithoutIdDT) {
        var id = UUID.randomUUID();
        var name = contextWithoutIdDT.getName();
        var description = contextWithoutIdDT.getDescription();
        var context = Context.of(id, name, description);
        return context.isValid().fold(
            Either::left,
            contextValid -> Either.right(
                DomainResult.of(contextValid, ContextCreated.of(contextValid.getId(), contextValid.getName()))
            )
        );
    }

    public static Context of(UUID id, String name, String description) {
        return new Context(id, name, description);
    }

    public static Context of(String name, String description) {
        return new Context(null, name, description);
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

    public Either<ContextInvalid, Context> isValid() {
        var errors = new ArrayList<Error>();
        if (name == null || name.isBlank()) {
            errors.add(Error.of("field.name", "Name is required."));
        }
        if (description == null || description.isBlank()) {
            errors.add(Error.of("field.description", "Description is required."));
        }
        if (name != null && name.length() > 256) {
            errors.add(Error.of("field.name", "Name must be less than 256 characters."));
        }
        if (description != null && description.length() > 512) {
            errors.add(Error.of("field.description", "Description must be less than 512 characters."));
        }
        if (!errors.isEmpty()) {
            return Either.left(ContextInvalid.of("Invalid context.", "Cannot create a new Context with these values for this fields.", errors, Context.of(name, description)));
        }
        return Either.right(this);
    }
}
