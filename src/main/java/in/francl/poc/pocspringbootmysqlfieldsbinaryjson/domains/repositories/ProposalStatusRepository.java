package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProposalStatusRepository {

    Try<ProposalStatusDT> save(ProposalStatusDT proposalStatus);

    Try<ProposalStatusDT> delete(ProposalStatusDT proposalStatus);

    Try<Optional<ProposalStatusDT>> get(UUID id);

    Try<Pageable<ProposalStatusDT>> search(ProposalStatusDT proposalStatus, int page, int size, List<String> sortFields, String sortDirection);

}
