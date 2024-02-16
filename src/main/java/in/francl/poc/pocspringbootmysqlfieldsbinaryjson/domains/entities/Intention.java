package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.contracts.Entity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.intention.IntentionInvalid;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.DomainResult;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.intention.IntentionCreated;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;

import java.util.ArrayList;
import java.util.UUID;

public class Intention implements Entity<UUID> {

        private final UUID id;
        private final String name;
        private final String description;
        private final UUID contextId;

        protected Intention(UUID id, String name, String description, UUID contextId) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.contextId = contextId;
        }

        public static Intention of(UUID id, String name, String description, UUID contextId) {
            return new Intention(id, name, description, contextId);
        }

        public static Intention of(String name, String description, UUID context) {
            return new Intention(null, name, description, context);
        }

        public static Either<IntentionInvalid, DomainResult<Intention, IntentionCreated>> create(IntentionWithoutIdDT intentionWithoutIdDT) {
            var id = UUID.randomUUID();
            var name = intentionWithoutIdDT.getName();
            var description = intentionWithoutIdDT.getDescription();
            var contextId = intentionWithoutIdDT.getContextId();

            var intention = Intention.of(id, name, description, contextId);

            return intention.isValid().fold(
                Either::left,
                intentionValid -> Either.right(
                    DomainResult.of(intentionValid, IntentionCreated.of(intentionValid.getId(), intentionValid.getName()))
                )
            );
        }

        public Either<IntentionInvalid, Intention> isValid() {
            var errors = new ArrayList<Error>();
            if (name == null || name.isBlank()) {
                errors.add(Error.of("field.name", "Name is required"));
            }
            if (description == null || description.isBlank()) {
                errors.add(Error.of("field.description", "Description is required"));
            }
            if (contextId == null) {
                errors.add(Error.of("field.context", "Context ID is required"));
            }
            if (!errors.isEmpty()) {
                var intention = Intention.of(name, description, contextId);
                return Either.left(
                    IntentionInvalid.of(
                        "Invalid intention.",
                        "Cannot create a new Intention with these values for this fields.",
                        errors,
                        intention
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

        public UUID getContextId() {
            return contextId;
        }
}
