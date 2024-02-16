package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ProposalRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal.ProposalDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal.ProposalEntityDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod.ProposalRepositoryOnRdbQueryMethod;
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
public class ProposalRepositoryOnRdb implements ProposalRepository {

    private final ProposalRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod;

    public ProposalRepositoryOnRdb(ProposalRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod) {
        this.repositoryOnRdbQueryMethod = repositoryOnRdbQueryMethod;
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
                    ExampleMatcher.matchingAny()
                        .withIgnoreNullValues()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                );
                var entityPage = repositoryOnRdbQueryMethod.findAll(example, pageRequest);
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
