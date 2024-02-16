package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.context.ContextDTImpl;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ContextEntity;

import java.util.function.Function;

public class ContextDTAssembler implements Function<ContextEntity, ContextDT> {

    @Override
    public ContextDT apply(ContextEntity context) {
        return ContextDTImpl.of(
            context.getId(),
            context.getName(),
            context.getDescription()
        );
    }

}
