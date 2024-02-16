package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ContextEntity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.IntentionEntity;

import java.util.function.Function;

public class IntentionEntityDTAssembler implements Function<IntentionDT, IntentionEntity> {

    @Override
    public IntentionEntity apply(IntentionDT intention) {
        return IntentionEntity.of(
            intention.getId(),
            intention.getName(),
            intention.getDescription(),
            ContextEntity.of(intention.getContextId(), null, null)
        );
    }
}
