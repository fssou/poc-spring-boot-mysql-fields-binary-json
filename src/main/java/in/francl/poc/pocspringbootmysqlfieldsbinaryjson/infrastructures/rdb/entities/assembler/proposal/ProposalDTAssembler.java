package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.assembler.proposal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposal.ProposalDTImpl;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities.ProposalEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ProposalDTAssembler implements Function<ProposalEntity, ProposalDT> {

        @Override
        public ProposalDT apply(ProposalEntity proposalEntity) {
            var objectMapper = new ObjectMapper();
            try {
                return ProposalDTImpl.of(
                    proposalEntity.getId(),
                    proposalEntity.getCreatedAt(),
                    proposalEntity.getUpdatedAt(),
                    proposalEntity.getDeletedAt(),
                    objectMapper.readValue(proposalEntity.getMetadata(), objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class)),
                    proposalEntity.getPersonId(),
                    proposalEntity.getProcessId(),
                    proposalEntity.getStatus().getId(),
                    proposalEntity.getIntention().getId()
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
}
