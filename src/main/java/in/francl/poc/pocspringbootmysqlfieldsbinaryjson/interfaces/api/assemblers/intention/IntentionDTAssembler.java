package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Intention;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.intention.IntentionDTImpl;

import java.util.function.Function;

public class IntentionDTAssembler implements Function<Intention, IntentionDT> {
    @Override
    public IntentionDT apply(Intention intention) {
        return IntentionDTImpl.of(
            intention.getId(),
            intention.getName(),
            intention.getDescription(),
            intention.getContextId()
        );
    }
}
