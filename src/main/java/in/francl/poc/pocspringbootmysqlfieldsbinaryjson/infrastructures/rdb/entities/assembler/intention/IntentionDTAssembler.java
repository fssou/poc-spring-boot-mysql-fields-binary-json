package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.intention.IntentionDTImpl;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.IntentionEntity;

import java.util.function.Function;

public class IntentionDTAssembler implements Function<IntentionEntity, IntentionDT> {

    @Override
    public IntentionDT apply(IntentionEntity intention) {
        return IntentionDTImpl.of(
            intention.getId(),
            intention.getName(),
            intention.getDescription(),
            intention.getContext().getId()
        );
    }
}
