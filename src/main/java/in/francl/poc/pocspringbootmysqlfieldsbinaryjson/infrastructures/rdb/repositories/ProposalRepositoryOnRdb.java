package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ProposalRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalEntity;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal.ProposalDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal.ProposalEntityDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod.ProposalRepositoryOnRdbQueryMethod;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;

@Repository
public class ProposalRepositoryOnRdb implements ProposalRepository {

    private final ProposalRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod;
    private final ObjectMapper objectMapper;

    public ProposalRepositoryOnRdb(
        ProposalRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod,
        ObjectMapper objectMapper
    ) {
        this.repositoryOnRdbQueryMethod = repositoryOnRdbQueryMethod;
        this.objectMapper = objectMapper;
    }

    @Override
    public Try<ProposalDT> save(ProposalDT proposal) {
        var entity = Transformer.transform(proposal, ProposalEntityDTAssembler::new);
        return Try.of(
            () -> {
                var entitySaved = repositoryOnRdbQueryMethod.save(entity);
                return Transformer.transform(entitySaved, ProposalDTAssembler::new);
            }
        );
    }

    @Override
    public Try<ProposalDT> delete(ProposalDT proposal) {
        var entity = Transformer.transform(proposal, ProposalEntityDTAssembler::new);
        return Try.of(
            () -> {
                repositoryOnRdbQueryMethod.delete(entity);
                return proposal;
            }
        );
    }

    @Override
    public Try<Optional<ProposalDT>> get(UUID proposalId) {
        return Try.of(
            () -> repositoryOnRdbQueryMethod.findById(proposalId)
                .map(entity -> Transformer.transform(entity, ProposalDTAssembler::new))
        );
    }

    @Override
    public Try<Pageable<ProposalDT>> search(ProposalDT proposal, int page, int size, List<String> sortFields, String sortDirection) {
        return Try.of(
            () -> {
                var entity = Transformer.transform(proposal, ProposalEntityDTAssembler::new);
                var pageRequest = PageRequest.of(
                    page,
                    size,
                    Sort.Direction.fromOptionalString(sortDirection).orElse(Sort.DEFAULT_DIRECTION),
                    sortFields.toArray(String[]::new)
                );
                var example = Example.of(
                    entity,
                    ExampleMatcher.matchingAll()
                        .withIgnoreNullValues()
                        .withIgnoreCase()
                        .withIgnorePaths("metadata")
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                );
//                var entityPage = repositoryOnRdbQueryMethod.findAll(example, pageRequest);

                var metadata = proposal.getMetadata();
                /*
                 * Structure of the metadataStr:
                 * {
                 *      "jsonPath": ["value_to_search", "second_value_to_search"]
                 *      "jsonPath2": ["value_to_search", "second_value_to_search"]
                 * }
                 */
                Specification<ProposalEntity> specification = (root, query, criteriaBuilder) -> {
                    var predicates = new ArrayList<Predicate>();
                    var jsonPaths  = metadata.keySet();
                    for(var entry : metadata.entrySet()) {
                        var jsonPath = entry.getKey();
                        var valuesObj = entry.getValue();

                        if (!(valuesObj instanceof List)) {
                            throw new IllegalArgumentException("The values of the metadata must be a list of strings");
                        }
                        var values = (List<String>) valuesObj;

                        var expression = root.get("metadata");
                        var function = criteriaBuilder.function("json_extract", String.class, expression, criteriaBuilder.literal(jsonPath));
                        var predicate = criteriaBuilder.in(function);

                        values.forEach(predicate::value);
                        predicates.add(predicate);
                    }

                    var predicateExample = QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example, EscapeCharacter.DEFAULT);
                    predicates.add(predicateExample);
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                };

                var entityPage = repositoryOnRdbQueryMethod.findAll(specification, pageRequest);

                return Page.of(
                    entityPage.getContent().stream().map(_entity -> Transformer.transform(_entity, ProposalDTAssembler::new)).toList(),
                    entityPage.getTotalElements(),
                    entityPage.getNumber(),
                    entityPage.getTotalPages(),
                    entityPage.getContent().size()
                );
            }
        );
    }
}
