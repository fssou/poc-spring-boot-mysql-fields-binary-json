package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;

import java.util.UUID;

public class ContextDTImpl extends ContextWithoutIdDTImpl implements ContextDT {

    private final UUID id;

    protected ContextDTImpl(UUID id, String name, String description) {
        super(name, description);
        this.id = id;
    }

    public static ContextDT of(UUID id, String name, String description) {
        return new ContextDTImpl(id, name, description);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

}
