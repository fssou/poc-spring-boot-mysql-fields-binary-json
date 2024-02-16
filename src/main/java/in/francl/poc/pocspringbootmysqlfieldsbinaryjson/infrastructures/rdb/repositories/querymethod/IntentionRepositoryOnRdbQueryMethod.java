package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.IntentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IntentionRepositoryOnRdbQueryMethod extends JpaRepository<IntentionEntity, UUID> {
}
