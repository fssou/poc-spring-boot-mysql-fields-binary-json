package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events.context;

import java.util.UUID;

public class ContextCreated implements ContextEvent {

    private final UUID id;
    private final String name;

    private ContextCreated(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ContextCreated of(UUID id, String name) {
        return new ContextCreated(id, name);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
