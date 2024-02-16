package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProposalWithoutIdDT {

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    LocalDateTime getDeletedAt();

    String getMetadata();

    UUID getPersonId();

    UUID getProcessId();

    UUID getStatusId();

    UUID getIntentionId();

}
