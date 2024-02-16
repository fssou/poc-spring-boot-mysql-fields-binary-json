package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.proposal;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProposalDTImpl extends ProposalWithoutIdDTImpl implements ProposalDT {

    private final UUID id;

    private ProposalDTImpl(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String metadata,
        UUID personId,
        UUID processId,
        UUID statusId,
        UUID intentionId
    ) {
        super(
            createdAt,
            updatedAt,
            deletedAt,
            metadata,
            personId,
            processId,
            statusId,
            intentionId
        );
        this.id = id;
    }

    public static ProposalDT of(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String metadata,
        UUID personId,
        UUID processId,
        UUID statusId,
        UUID intentionId
    ) {
        return new ProposalDTImpl(
            id,
            createdAt,
            updatedAt,
            deletedAt,
            metadata,
            personId,
            processId,
            statusId,
            intentionId
        );
    }

    @Override
    public UUID getId() {
        return id;
    }
}
