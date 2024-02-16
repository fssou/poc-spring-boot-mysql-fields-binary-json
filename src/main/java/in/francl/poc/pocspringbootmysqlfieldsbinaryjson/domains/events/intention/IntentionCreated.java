package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.intention;

import java.util.UUID;

public class IntentionCreated implements IntentionEvent {
    private final UUID id;
    private final String name;

    private IntentionCreated(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static IntentionCreated of(UUID id, String name) {
        return new IntentionCreated(id, name);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
