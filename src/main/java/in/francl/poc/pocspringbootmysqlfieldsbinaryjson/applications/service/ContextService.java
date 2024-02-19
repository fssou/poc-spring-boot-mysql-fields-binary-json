package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.assemblers.context.ContextDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.NotFoundError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextWithoutIdDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Context;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ContextRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.transformers.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContextService extends ServiceBase {

    private final ContextRepository contextRepository;

    public ContextService(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Transactional
    public Either<ServiceError, ContextDT> createAndSave(ContextWithoutIdDT contextWithoutIdDT) {
        var contextCreatedDomain = Context.create(contextWithoutIdDT);
        if(contextCreatedDomain.isLeft()) {
            return Either.left(asServiceError(contextCreatedDomain.getLeft()));
        }
        var context = contextCreatedDomain.getRight().getEntity();
        var contextDT = Transformer.transform(context, ContextDTAssembler::new);
        var contextCreatedTry = contextRepository.save(contextDT);
        if (contextCreatedTry.isFailure())
            return Either.left(asServiceError(contextCreatedTry));
        return Either.right(contextCreatedTry.get());
    }

    @Transactional
    public Either<ServiceError, ContextDT> delete(String id) {
        var contextTry = contextRepository.get(asUUID(id));
        if(contextTry.isFailure())
            return Either.left(asServiceError(contextTry));
        var contextOptional = contextTry.get();
        if (contextOptional.isEmpty())
            return Either.left(
                NotFoundError.of("Context not found", "Context not found")
            );
        var context = contextOptional.get();
        var contextDeletedTry = contextRepository.delete(context);
        if (contextDeletedTry.isFailure())
            return Either.left(asServiceError(contextDeletedTry));
        return Either.right(contextDeletedTry.get());
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, ContextDT> get(String id) {
        var contextTry = contextRepository.get(asUUID(id));
        if(contextTry.isFailure())
            return Either.left(asServiceError(contextTry));
        var contextOptional = contextTry.get();
        if (contextOptional.isEmpty())
            return Either.left(
                NotFoundError.of("Context not found", "Context not found")
            );
        var context = contextOptional.get();
        return Either.right(context);
    }

    @Transactional(readOnly = true)
    public Either<ServiceError, Pageable<ContextDT>> search(ContextDT context, int page, int size, List<String> sortFields, String sortDirection) {
        var contextTry = contextRepository.search(context, page, size, sortFields, sortDirection);
        if(contextTry.isFailure())
            return Either.left(asServiceError(contextTry));
        var contextIterable = contextTry.get();
        return Either.right(contextIterable);
    }

}
