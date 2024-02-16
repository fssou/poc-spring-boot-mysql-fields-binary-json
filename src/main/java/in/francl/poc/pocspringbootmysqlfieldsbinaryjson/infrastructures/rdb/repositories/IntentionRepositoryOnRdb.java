package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.IntentionRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.intention.IntentionDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.intention.IntentionEntityDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod.IntentionRepositoryOnRdbQueryMethod;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class IntentionRepositoryOnRdb implements IntentionRepository {

    private final IntentionRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod;

    public IntentionRepositoryOnRdb(IntentionRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod) {
        this.repositoryOnRdbQueryMethod = repositoryOnRdbQueryMethod;
    }

    @Override
    public Try<IntentionDT> save(IntentionDT intention) {
        var intentionEntity = Transformer.transform(intention, IntentionEntityDTAssembler::new);
        return Try.of(
            () -> {
                var entity = repositoryOnRdbQueryMethod.save(intentionEntity);
                return Transformer.transform(entity, IntentionDTAssembler::new);
            }
        );
    }

    @Override
    public Try<IntentionDT> delete(IntentionDT intention) {
        var intentionEntity = Transformer.transform(intention, IntentionEntityDTAssembler::new);
        return Try.of(
            () -> {
                repositoryOnRdbQueryMethod.delete(intentionEntity);
                return intention;
            }
        );
    }

    @Override
    public Try<Optional<IntentionDT>> get(UUID intentionId) {
        return Try.of(
            () -> repositoryOnRdbQueryMethod.findById(intentionId)
                .map(entity -> Transformer.transform(entity, IntentionDTAssembler::new))
        );
    }

    @Override
    public Try<Pageable<IntentionDT>> search(IntentionDT intention, int page, int size, List<String> sortFields, String sortDirection) {
        return Try.of(
            () -> {
                var intentionEntity = Transformer.transform(intention, IntentionEntityDTAssembler::new);
                var pageRequest = PageRequest.of(
                    page,
                    size,
                    Sort.Direction.fromOptionalString(sortDirection).orElse(Sort.DEFAULT_DIRECTION),
                    sortFields.toArray(String[]::new)
                );
                var example = Example.of(
                    intentionEntity,
                    ExampleMatcher.matchingAny()
                        .withIgnoreNullValues()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                );
                var pageContextEntity = repositoryOnRdbQueryMethod.findAll(example, pageRequest);

                return Page.of(
                    pageContextEntity.getContent().stream().map(entity -> Transformer.transform(entity, IntentionDTAssembler::new)).toList(),
                    pageContextEntity.getTotalElements(),
                    pageContextEntity.getNumber(),
                    pageContextEntity.getTotalPages(),
                    pageContextEntity.getContent().size()
                );
            }
        );
    }
}
