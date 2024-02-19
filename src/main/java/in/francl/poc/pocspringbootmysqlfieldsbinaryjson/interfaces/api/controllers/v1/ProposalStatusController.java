package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.v1;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service.ProposalStatusService;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.ProposalStatus;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.proposalstatus.ProposalStatusDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.BaseController;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposalstatus.ProposalStatusDTRequestWithoutId;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response.Response;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.datetime.Timer;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils.transformers.Transformer;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/v1/proposalStatuses")
public class ProposalStatusController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProposalStatusController.class);

    private final ProposalStatusService proposalStatusService;

    public ProposalStatusController(ProposalStatusService proposalStatusService) {
        this.proposalStatusService = proposalStatusService;
    }

    @PostMapping(
        consumes = "application/json",
        produces = "application/json"
    )
    public ResponseEntity<?> create(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @RequestBody ProposalStatusDTRequestWithoutId proposalStatusDT
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("proposalStatus", proposalStatusDT),
            "Creating proposalStatus"
        );
        Either<ServiceError, ProposalStatusDT> proposalStatusCreatedEither = proposalStatusService.createAndSave(proposalStatusDT);
        if (proposalStatusCreatedEither.isLeft()) {
            var left = proposalStatusCreatedEither.getLeft();
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
                        "proposalStatus", proposalStatusDT
                    )
                ),
                "Error creating proposalStatus",
                proposalStatusCreatedEither.getLeft()
            );
            return ResponseEntity.badRequest().body(response);
        }
        var response = Response.withResponseData(
            "201",
            "Proposal Status created",
            "Proposal Status created",
            proposalStatusCreatedEither.getRight()
        );
        LOGGER.info(
            Markers.appendEntries(
                Map.of(
                    "proposalStatus", proposalStatusCreatedEither.getRight(),
                    "duration", timer.stop().toMillis()
                )
            ),
            "Proposal Status created"
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
            Markers.append("proposalStatusId", id),
            "Deleting proposal status"
        );
        Either<ServiceError, ProposalStatusDT> proposalStatusEither = proposalStatusService.delete(id);
        return proposalStatusEither.fold(
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
                            "proposalStatusId", id
                        )
                    ),
                    "Error deleting proposal status",
                    proposalStatusEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Proposal Status deleted",
                    "Proposal Status deleted",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "proposalStatus", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Proposal Status deleted"
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
            Markers.append("proposalStatusID", id),
            "Getting proposal status"
        );
        Either<ServiceError, ProposalStatusDT> proposalStatusEither = proposalStatusService.get(id);
        return proposalStatusEither.fold(
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
                            "proposalStatusId", id
                        )
                    ),
                    "Error getting proposal status",
                    proposalStatusEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Proposal Status found",
                    "Proposal Status found",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "proposalStatus", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Proposal Status found"
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
        @RequestParam Map<String, String> proposalStatusRequestParam
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("proposalStatus", proposalStatusRequestParam),
            "Searching proposal status"
        );
        String id = proposalStatusRequestParam.get("id");
        String name = proposalStatusRequestParam.get("name");
        String description = proposalStatusRequestParam.get("description");
        var proposalStatus = ProposalStatus.of(
            id != null ? UUID.fromString(id) : null,
            name,
            description
        );
        var proposalStatusDT = Transformer.transform(proposalStatus, ProposalStatusDTAssembler::new);
        Either<ServiceError, Pageable<ProposalStatusDT>> proposalStatusEither = proposalStatusService.search(proposalStatusDT, page, size, sortFields, sortDirection);
        return proposalStatusEither.fold(
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
                            "proposalStatus", proposalStatusRequestParam
                        )
                    ),
                    "Error searching proposal status",
                    proposalStatusEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseDataPaginated(
                    "200",
                    "Proposal status found",
                    "Proposal status found",
                    right.getItems(),
                    right.getTotal(),
                    right.getPageTotal(),
                    right.getPageNumber(),
                    right.getPageSize()
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "proposalStatus", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Proposal Status found"
                );
                MDC.clear();
                return ResponseEntity.ok(response);
            }
        );
    }
}
