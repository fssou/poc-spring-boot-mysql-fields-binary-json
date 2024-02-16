package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;

import java.util.UUID;

public class IntentionDTImpl implements IntentionDT {

    private final UUID id;
    private final String name;
    private final String description;
    private final UUID contextId;

    private IntentionDTImpl(UUID id, String name, String description, UUID contextId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contextId = contextId;
    }

    public static IntentionDTImpl of(
        UUID id,
        String name,
        String description,
        UUID contextId
    ) {
        return new IntentionDTImpl(id, name, description, contextId);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public UUID getContextId() {
        return contextId;
    }
}
