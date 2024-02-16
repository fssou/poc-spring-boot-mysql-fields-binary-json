package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.datatransfers.context.ContextDTImpl;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Context;

import java.util.function.Function;

public class ContextDTAssembler implements Function<Context, ContextDT> {
    @Override
    public ContextDT apply(Context context) {
        return ContextDTImpl.of(
            context.getId(),
            context.getName(),
            context.getDescription()
        );
    }
}
