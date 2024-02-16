package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.datatransfers.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;

import java.util.UUID;

public class ContextDTImpl implements ContextDT {

    private final UUID id;
    private final String name;
    private final String description;

    private ContextDTImpl(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ContextDTImpl of(UUID id, String name, String description) {
        return new ContextDTImpl(id, name, description);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
