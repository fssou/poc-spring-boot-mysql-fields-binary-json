package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.repositories.ProposalStatusRepository;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposalstatus.ProposalStatusDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposalstatus.ProposalStatusEntityDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod.ProposalStatusRepositoryOnRdbQueryMethod;
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
public class ProposalStatusRepositoryOnRdb implements ProposalStatusRepository {

    private final ProposalStatusRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod;

    public ProposalStatusRepositoryOnRdb(ProposalStatusRepositoryOnRdbQueryMethod repositoryOnRdbQueryMethod) {
        this.repositoryOnRdbQueryMethod = repositoryOnRdbQueryMethod;
    }

    @Override
    public Try<ProposalStatusDT> save(ProposalStatusDT proposalStatus) {
        var proposalStatusEntity = Transformer.transform(proposalStatus, ProposalStatusEntityDTAssembler::new);
        return Try.of(
            () -> {
                var entity = repositoryOnRdbQueryMethod.save(proposalStatusEntity);
                return Transformer.transform(entity, ProposalStatusDTAssembler::new);
            }
        );
    }

    @Override
    public Try<ProposalStatusDT> delete(ProposalStatusDT proposalStatus) {
        var proposalStatusEntity = Transformer.transform(proposalStatus, ProposalStatusEntityDTAssembler::new);
        return Try.of(
            () -> {
                repositoryOnRdbQueryMethod.delete(proposalStatusEntity);
                return proposalStatus;
            }
        );
    }

    @Override
    public Try<Optional<ProposalStatusDT>> get(UUID proposalStatusId) {
        return Try.of(
            () -> repositoryOnRdbQueryMethod.findById(proposalStatusId)
                .map(proposalStatus -> Transformer.transform(proposalStatus, ProposalStatusDTAssembler::new))
        );
    }

    @Override
    public Try<Pageable<ProposalStatusDT>> search(ProposalStatusDT proposalStatus, int page, int size, List<String> sortFields, String sortDirection) {
        return Try.of(
            () -> {
                var entity = Transformer.transform(proposalStatus, ProposalStatusEntityDTAssembler::new);
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
                    entityPage.getContent().stream().map(_entity -> Transformer.transform(_entity, ProposalStatusDTAssembler::new)).toList(),
                    entityPage.getTotalElements(),
                    entityPage.getNumber(),
                    entityPage.getTotalPages(),
                    entityPage.getContent().size()
                );
            }
        );
    }
}
