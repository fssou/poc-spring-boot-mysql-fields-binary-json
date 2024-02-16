package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.intention.IntentionDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.NotFoundError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Intention;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.IntentionRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IntentionService extends ServiceBase {

    private final IntentionRepository intentionRepository;

    public IntentionService(IntentionRepository intentionRepository) {
        this.intentionRepository = intentionRepository;
    }

    @Transactional
    public Either<ServiceError, IntentionDT> createAndSave(IntentionWithoutIdDT intentionWithoutIdDT) {
        var intentionCreatedDomain = Intention.create(intentionWithoutIdDT);
        if(intentionCreatedDomain.isLeft()) {
            return Either.left(asServiceError(intentionCreatedDomain.getLeft()));
        }
        var intention = intentionCreatedDomain.getRight().getEntity();
        var intentionDT = Transformer.transform(intention, IntentionDTAssembler::new);
        var intentionCreatedTry = intentionRepository.save(intentionDT);
        if (intentionCreatedTry.isFailure())
            return Either.left(asServiceError(intentionCreatedTry));
        return Either.right(intentionCreatedTry.get());
    }

    @Transactional
    public Either<ServiceError, IntentionDT> delete(String id) {
        var intentionTry = intentionRepository.get(asUUID(id));
        if(intentionTry.isFailure())
            return Either.left(asServiceError(intentionTry));
        var intentionOptional = intentionTry.get();
        if (intentionOptional.isEmpty())
            return Either.left(
                NotFoundError.of("Intention not found", "Intention not found for ID '%s'".formatted(id))
            );
        var intention = intentionOptional.get();
        var intentionDeletedTry = intentionRepository.delete(intention);
        if (intentionDeletedTry.isFailure())
            return Either.left(asServiceError(intentionDeletedTry));
        return Either.right(intentionDeletedTry.get());
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, IntentionDT> get(String id) {
        var intentionTry = intentionRepository.get(asUUID(id));
        if(intentionTry.isFailure())
            return Either.left(asServiceError(intentionTry));
        var intentionOptional = intentionTry.get();
        if (intentionOptional.isEmpty())
            return Either.left(
                NotFoundError.of("Intention not found", "Intention not found")
            );
        var intention = intentionOptional.get();
        return Either.right(intention);
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, Pageable<IntentionDT>> search(IntentionDT intention, int page, int size, List<String> sortFields, String sortDirection) {
        var intentionTry = intentionRepository.search(intention, page, size, sortFields, sortDirection);
        if(intentionTry.isFailure())
            return Either.left(asServiceError(intentionTry));
        var intentionIterable = intentionTry.get();
        return Either.right(intentionIterable);
    }

}
