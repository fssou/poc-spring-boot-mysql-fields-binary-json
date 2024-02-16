package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProposalRepository {

    Try<ProposalDT> save(ProposalDT proposal);

    Try<ProposalDT> delete(ProposalDT proposal);

    Try<Optional<ProposalDT>> get(UUID id);

    Try<Pageable<ProposalDT>> search(ProposalDT proposal, int page, int size, List<String> sortFields, String sortDirection);

}
