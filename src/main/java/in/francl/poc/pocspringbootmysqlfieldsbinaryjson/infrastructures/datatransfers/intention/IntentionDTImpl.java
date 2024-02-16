package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;

import java.util.UUID;

public class IntentionDTImpl extends IntentionWithoutIdDTImpl implements IntentionDT {

    private final UUID id;

    private IntentionDTImpl(UUID id, String name, String description, UUID contextId) {
        super(name, description, contextId);
        this.id = id;
    }

    public static IntentionDTImpl of(UUID id, String name, String description, UUID contextId) {
        return new IntentionDTImpl(id, name, description, contextId);
    }

    public UUID getId() {
        return id;
    }

}
