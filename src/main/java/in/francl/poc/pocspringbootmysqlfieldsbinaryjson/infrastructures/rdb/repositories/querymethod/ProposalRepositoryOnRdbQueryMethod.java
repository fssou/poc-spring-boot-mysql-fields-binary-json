package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProposalRepositoryOnRdbQueryMethod extends JpaRepository<ProposalEntity, UUID> {
}
