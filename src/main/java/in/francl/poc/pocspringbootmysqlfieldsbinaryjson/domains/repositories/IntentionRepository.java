package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IntentionRepository {

    Try<IntentionDT> save(IntentionDT intention);

    Try<IntentionDT> delete(IntentionDT intention);

    Try<Optional<IntentionDT>> get(UUID id);

    Try<Pageable<IntentionDT>> search(IntentionDT intention, int page, int size, List<String> sortFields, String sortDirection);

}
