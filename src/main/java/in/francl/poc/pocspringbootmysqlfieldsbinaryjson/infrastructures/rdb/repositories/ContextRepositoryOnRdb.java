package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ContextRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.context.ContextDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.context.ContextEntityDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod.ContextRepositoryOnRdbQueryMethod;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.transformers.Transformer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContextRepositoryOnRdb implements ContextRepository {

    private final ContextRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod;

    public ContextRepositoryOnRdb(ContextRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod) {
        this.repositoryOnRdbQueryMethod = repositoryOnRdbQueryMethod;
    }

    @Override
    public Try<ContextDT> save(ContextDT context) {
        var contextEntity = Transformer.transform(context, ContextEntityDTAssembler::new);
        return Try.of(
            () -> {
                var entity = repositoryOnRdbQueryMethod.save(contextEntity);
                return Transformer.transform(entity, ContextDTAssembler::new);
            }
        );
    }

    @Override
    public Try<ContextDT> delete(ContextDT context) {
        var contextEntity = Transformer.transform(context, ContextEntityDTAssembler::new);
        return Try.of(
            () -> {
                repositoryOnRdbQueryMethod.delete(contextEntity);
                return context;
            }
        );
    }

    @Override
    public Try<Optional<ContextDT>> get(UUID contextId) {
        return Try.of(
            () -> repositoryOnRdbQueryMethod.findById(contextId)
                .map(context -> Transformer.transform(context, ContextDTAssembler::new))
        );
    }

    @Override
    public Try<Pageable<ContextDT>> search(ContextDT context, int page, int size, List<String> sortFields, String sortDirection) {
        return Try.of(
            () -> {
                var contextEntity = Transformer.transform(context, ContextEntityDTAssembler::new);
                var pageRequest = PageRequest.of(
                    page,
                    size,
                    Direction.fromOptionalString(sortDirection).orElse(Sort.DEFAULT_DIRECTION),
                    sortFields.toArray(String[]::new)
                );
                var example = Example.of(
                    contextEntity,
                    ExampleMatcher.matchingAny()
                        .withIgnoreNullValues()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                );
                var pageContextEntity = repositoryOnRdbQueryMethod.findAll(example, pageRequest);

                return Page.of(
                    pageContextEntity.getContent().stream().map(entity -> Transformer.transform(entity, ContextDTAssembler::new)).toList(),
                    pageContextEntity.getTotalElements(),
                    pageContextEntity.getNumber(),
                    pageContextEntity.getTotalPages(),
                    pageContextEntity.getContent().size()
                );
            }
        );
    }
}
