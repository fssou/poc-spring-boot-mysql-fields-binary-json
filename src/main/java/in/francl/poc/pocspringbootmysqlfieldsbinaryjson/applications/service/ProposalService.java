package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.proposal.ProposalDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.proposalstatus.ProposalStatusDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.NotFoundError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ProposalRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProposalService extends ServiceBase {

    private final ProposalRepository proposalRepository;

    protected ProposalService(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Transactional
    public Either<ServiceError, ProposalDT> createAndSave(ProposalWithoutIdDT proposalWithoutIdDT) {
        var proposalCreatedDomain = Proposal.create(proposalWithoutIdDT);
        if (proposalCreatedDomain.isLeft()) {
            return Either.left(asServiceError(proposalCreatedDomain.getLeft()));
        }
        var proposal = proposalCreatedDomain.getRight().getEntity();
        var proposalDT = Transformer.transform(proposal, ProposalDTAssembler::new);
        var proposalCreatedTry = proposalRepository.save(proposalDT);
        if (proposalCreatedTry.isFailure())
            return Either.left(asServiceError(proposalCreatedTry));
        return Either.right(proposalCreatedTry.get());
    }

    @Transactional
    public Either<ServiceError, ProposalDT> delete(String id) {
        var proposalTry = proposalRepository.get(asUUID(id));
        if (proposalTry.isFailure())
            return Either.left(asServiceError(proposalTry));
        var proposalOptional = proposalTry.get();
        if (proposalOptional.isEmpty())
            return Either.left(
                NotFoundError.of("Proposal not found", "Proposal not found for ID '%s'".formatted(id))
            );
        var proposal = proposalOptional.get();
        var proposalDeletedTry = proposalRepository.delete(proposal);
        if (proposalDeletedTry.isFailure())
            return Either.left(asServiceError(proposalDeletedTry));
        return Either.right(proposalDeletedTry.get());
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, ProposalDT> get(String id) {
        var proposalTry = proposalRepository.get(asUUID(id));
        if (proposalTry.isFailure())
            return Either.left(asServiceError(proposalTry));
        var proposalOptional = proposalTry.get();
        if (proposalOptional.isEmpty())
            return Either.left(
                NotFoundError.of("Proposal not found", "Proposal not found for ID '%s'".formatted(id))
            );
        var proposalStatus = proposalOptional.get();
        return Either.right(proposalStatus);
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, Pageable<ProposalDT>> search(ProposalDT proposal, int page, int size, List<String> sortFields, String sortDirection) {
        var proposalSearchTry = proposalRepository.search(proposal, page, size, sortFields, sortDirection);
        if (proposalSearchTry.isFailure())
            return Either.left(asServiceError(proposalSearchTry));
        var proposalSearchPage = proposalSearchTry.get();
        return Either.right(proposalSearchPage);
    }

}
