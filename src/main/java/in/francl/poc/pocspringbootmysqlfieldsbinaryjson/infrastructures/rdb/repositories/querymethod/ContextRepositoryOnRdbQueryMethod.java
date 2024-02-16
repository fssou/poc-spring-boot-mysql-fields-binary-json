package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories.querymethod;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ContextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContextRepositoryOnRdbQueryMethod extends JpaRepository<ContextEntity, UUID> {
}
