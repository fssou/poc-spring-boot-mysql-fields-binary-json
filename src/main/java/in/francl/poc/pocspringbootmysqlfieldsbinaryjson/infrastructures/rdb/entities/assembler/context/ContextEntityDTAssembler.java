package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.context;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ContextEntity;

import java.util.function.Function;

public final class ContextEntityDTAssembler implements Function<ContextDT, ContextEntity> {

    @Override
    public ContextEntity apply(ContextDT context) {
        return ContextEntity.of(
            context.getId(),
            context.getName(),
            context.getDescription()
        );
    }

}
