package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextWithoutIdDT;

public class ContextWithoutIdDTImpl implements ContextWithoutIdDT {

    private final String name;
    private final String description;

    protected ContextWithoutIdDTImpl(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ContextWithoutIdDTImpl of(String name, String description) {
        return new ContextWithoutIdDTImpl(name, description);
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
