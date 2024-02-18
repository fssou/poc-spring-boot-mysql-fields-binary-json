package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service.ProposalService;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposal.ProposalDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Proposal;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try.Try;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.proposal.ProposalDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.proposalstatus.ProposalStatusDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.BaseController;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposal.ProposalDTRequestWithoutId;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response.Response;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Timer;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.Transformer;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/v1/proposals")
public class ProposalController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProposalController.class);

    private final ProposalService proposalService;
    private final ObjectMapper objectMapper;

    public ProposalController(
        ProposalService proposalService,
        ObjectMapper objectMapper
    ) {
        this.proposalService = proposalService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(
        consumes = "application/json",
        produces = "application/json"
    )
    public ResponseEntity<?> create(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @RequestBody ProposalDTRequestWithoutId proposalDT
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("proposal", proposalDT),
            "Creating proposal"
        );
        Either<ServiceError, ProposalDT> proposalCreatedEither = proposalService.createAndSave(proposalDT);
        if (proposalCreatedEither.isLeft()) {
            var left = proposalCreatedEither.getLeft();
            var response = Response.withResponseError(
                left.getCode(),
                left.getMessage(),
                left.getDetails(),
                left.getErrors()
            );
            LOGGER.error(
                Markers.appendEntries(
                    Map.of(
                        "duration", timer.stop().toMillis(),
                        "proposal", proposalDT
                    )
                ),
                "Error creating proposal",
                proposalCreatedEither.getLeft()
            );
            return ResponseEntity.badRequest().body(response);
        }
        var response = Response.withResponseData(
            "201",
            "Proposal created",
            "Proposal created",
            proposalCreatedEither.getRight()
        );
        LOGGER.info(
            Markers.appendEntries(
                Map.of(
                    "proposal", proposalCreatedEither.getRight(),
                    "duration", timer.stop().toMillis()
                )
            ),
            "Proposal created"
        );
        MDC.clear();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(
        value = "/{id}",
        produces = "application/json"
    )
    public ResponseEntity<?> delete(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @PathVariable("id") String id
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("proposalId", id),
            "Deleting proposal"
        );
        Either<ServiceError, ProposalDT> proposalEither = proposalService.delete(id);
        return proposalEither.fold(
            left -> {
                var response = Response.withResponseError(
                    left.getCode(),
                    left.getMessage(),
                    left.getDetails(),
                    left.getErrors()
                );
                LOGGER.error(
                    Markers.appendEntries(
                        Map.of(
                            "duration", timer.stop().toMillis(),
                            "proposalId", id
                        )
                    ),
                    "Error deleting proposal",
                    proposalEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Proposal deleted",
                    "Proposal deleted",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "proposal", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Proposal deleted"
                );
                MDC.clear();
                return ResponseEntity.ok(response);
            }
        );
    }

    @GetMapping(
        value = "/{id}",
        produces = "application/json"
    )
    public ResponseEntity<?> get(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @PathVariable("id") String id
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("proposalId", id),
            "Getting proposal"
        );
        Either<ServiceError, ProposalDT> proposalEither = proposalService.get(id);
        return proposalEither.fold(
            left -> {
                var response = Response.withResponseError(
                    left.getCode(),
                    left.getMessage(),
                    left.getDetails(),
                    left.getErrors()
                );
                LOGGER.error(
                    Markers.appendEntries(
                        Map.of(
                            "duration", timer.stop().toMillis(),
                            "proposalId", id
                        )
                    ),
                    "Error getting proposal",
                    proposalEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Proposal found",
                    "Proposal found",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "proposal", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Proposal found"
                );
                MDC.clear();
                return ResponseEntity.ok(response);
            }
        );
    }

    @GetMapping(
        produces = "application/json"
    )
    public ResponseEntity<?> search(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "sortFields", defaultValue = "id") List<String> sortFields,
        @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection,
        @RequestParam Map<String, String> proposalRequestParam
    ) throws JsonProcessingException {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("proposal", proposalRequestParam),
            "Searching proposal"
        );
        String id = proposalRequestParam.get("id");
        String createdAt = proposalRequestParam.get("createdAt");
        String updatedAt = proposalRequestParam.get("updatedAt");
        String deletedAt = proposalRequestParam.get("deletedAt");
        String personId = proposalRequestParam.get("personId");
        String processId = proposalRequestParam.get("processId");
        String statusId = proposalRequestParam.get("statusId");
        String intentionId = proposalRequestParam.get("intentionId");
        String metadataStr = proposalRequestParam.get("metadata");
        var metadata = objectMapper.readValue(metadataStr, Map.class);
        var proposal = Proposal.of(
            id != null ? UUID.fromString(id) : null,
            Try.of( () -> LocalDateTime.parse(createdAt)).fold(
                e -> null,
                Try::get
            ),
            Try.of( () -> LocalDateTime.parse(updatedAt)).fold(
                e -> null,
                Try::get
            ),
            Try.of( () -> LocalDateTime.parse(deletedAt)).fold(
                e -> null,
                Try::get
            ),
            metadata,
            personId != null ? UUID.fromString(personId) : null,
            processId != null ? UUID.fromString(processId) : null,
            statusId != null ? UUID.fromString(statusId) : null,
            intentionId != null ? UUID.fromString(intentionId) : null
        );
        var proposalDT = Transformer.transform(proposal, ProposalDTAssembler::new);
        Either<ServiceError, Pageable<ProposalDT>> proposalEither = proposalService.search(proposalDT, page, size, sortFields, sortDirection);
        return proposalEither.fold(
            left -> {
                var response = Response.withResponseError(
                    left.getCode(),
                    left.getMessage(),
                    left.getDetails(),
                    left.getErrors()
                );
                LOGGER.error(
                    Markers.appendEntries(
                        Map.of(
                            "duration", timer.stop().toMillis(),
                            "proposal", proposalRequestParam
                        )
                    ),
                    "Error searching proposal",
                    proposalEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseDataPaginated(
                    "200",
                    "Proposal found",
                    "Proposal found",
                    right.getItems(),
                    right.getTotal(),
                    right.getPageTotal(),
                    right.getPageNumber(),
                    right.getPageSize()
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "proposal", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Proposal found"
                );
                MDC.clear();
                return ResponseEntity.ok(response);
            }
        );
    }

}
