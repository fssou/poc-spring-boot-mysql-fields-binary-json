package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.proposalstatus.ProposalStatusDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.NotFoundError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ProposalStatusRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProposalStatusService extends ServiceBase {
    private final ProposalStatusRepository proposalStatusRepository;

    public ProposalStatusService(ProposalStatusRepository proposalStatusRepository) {
        this.proposalStatusRepository = proposalStatusRepository;
    }

    @Transactional
    public Either<ServiceError, ProposalStatusDT> createAndSave(ProposalStatusWithoutIdDT proposalStatusWithoutIdDT) {
        var proposalStatusCreatedDomain = ProposalStatus.create(proposalStatusWithoutIdDT);
        if (proposalStatusCreatedDomain.isLeft()) {
            return Either.left(asServiceError(proposalStatusCreatedDomain.getLeft()));
        }
        var proposalStatus = proposalStatusCreatedDomain.getRight().getEntity();
        var proposalStatusDT = Transformer.transform(proposalStatus, ProposalStatusDTAssembler::new);
        var proposalStatusCreatedTry = proposalStatusRepository.save(proposalStatusDT);
        if (proposalStatusCreatedTry.isFailure())
            return Either.left(asServiceError(proposalStatusCreatedTry));
        return Either.right(proposalStatusCreatedTry.get());
    }

    @Transactional
    public Either<ServiceError, ProposalStatusDT> delete(String id) {
        var proposalStatusTry = proposalStatusRepository.get(asUUID(id));
        if (proposalStatusTry.isFailure())
            return Either.left(asServiceError(proposalStatusTry));
        var proposalStatusOptional = proposalStatusTry.get();
        if (proposalStatusOptional.isEmpty())
            return Either.left(
                NotFoundError.of("ProposalStatus not found", "ProposalStatus not found for ID '%s'".formatted(id))
            );
        var proposalStatus = proposalStatusOptional.get();
        var proposalStatusDeletedTry = proposalStatusRepository.delete(proposalStatus);
        if (proposalStatusDeletedTry.isFailure())
            return Either.left(asServiceError(proposalStatusDeletedTry));
        return Either.right(proposalStatusDeletedTry.get());
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, ProposalStatusDT> get(String id) {
        var proposalStatusTry = proposalStatusRepository.get(asUUID(id));
        if (proposalStatusTry.isFailure())
            return Either.left(asServiceError(proposalStatusTry));
        var proposalStatusOptional = proposalStatusTry.get();
        if (proposalStatusOptional.isEmpty())
            return Either.left(
                NotFoundError.of("ProposalStatus not found", "ProposalStatus not found for ID '%s'".formatted(id))
            );
        var proposalStatus = proposalStatusOptional.get();
        return Either.right(proposalStatus);
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, Pageable<ProposalStatusDT>> search(ProposalStatusDT intention, int page, int size, List<String> sortFields, String sortDirection) {
        var proposalStatusSearchTry = proposalStatusRepository.search(intention, page, size, sortFields, sortDirection);
        if (proposalStatusSearchTry.isFailure())
            return Either.left(asServiceError(proposalStatusSearchTry));
        var proposalStatusSearchPage = proposalStatusSearchTry.get();
        return Either.right(proposalStatusSearchPage);
    }

}
