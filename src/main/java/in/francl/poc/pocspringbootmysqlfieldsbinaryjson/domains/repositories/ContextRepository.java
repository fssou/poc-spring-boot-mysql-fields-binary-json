package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContextRepository {

    Try<ContextDT> save(ContextDT context);

    Try<ContextDT> delete(ContextDT context);

    Try<Optional<ContextDT>> get(UUID id);

    Try<Pageable<ContextDT>> search(ContextDT context, int page, int size, List<String> sortFields, String sortDirection);

}
