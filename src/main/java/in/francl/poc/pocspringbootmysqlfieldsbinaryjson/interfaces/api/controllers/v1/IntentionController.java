package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.v1;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service.IntentionService;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Context;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Intention;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.context.ContextDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.intention.IntentionDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.BaseController;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.context.ContextWithoutIdRequestDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.intention.IntentionDTRequestWithoutId;
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

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/v1/intentions")
public class IntentionController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IntentionController.class);

    private final IntentionService intentionService;

    public IntentionController(IntentionService intentionService) {
        this.intentionService = intentionService;
    }

    @PostMapping(
        consumes = "application/json",
        produces = "application/json"
    )
    public ResponseEntity<?> create(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @RequestBody IntentionDTRequestWithoutId intentionDT
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("intention", intentionDT),
            "Creating intention with name: {}",
            intentionDT.getName()
        );
        Either<ServiceError, IntentionDT> intentionCreatedEither = intentionService.createAndSave(intentionDT);
        if (intentionCreatedEither.isLeft()) {
            var left = intentionCreatedEither.getLeft();
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
                        "intention", intentionDT
                    )
                ),
                "Error creating intention",
                intentionCreatedEither.getLeft()
            );
            return ResponseEntity.badRequest().body(response);
        }
        var response = Response.withResponseData(
            "201",
            "Intention created",
            "Intention created",
            intentionCreatedEither.getRight()
        );
        LOGGER.info(
            Markers.appendEntries(
                Map.of(
                    "intention", intentionCreatedEither.getRight(),
                    "duration", timer.stop().toMillis()
                )
            ),
            "Intention created"
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
            Markers.append("intentionID", id),
            "Deleting context with id: {}",
            id
        );
        Either<ServiceError, IntentionDT> intentionEither = intentionService.delete(id);
        return intentionEither.fold(
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
                            "intentionId", id
                        )
                    ),
                    "Error deleting intention",
                    intentionEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Intention deleted",
                    "Intention deleted",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "intention", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Intention deleted"
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
            Markers.append("intentionID", id),
            "Getting intention"
        );
        Either<ServiceError, IntentionDT> intentionEither = intentionService.get(id);
        return intentionEither.fold(
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
                            "intentionId", id
                        )
                    ),
                    "Error getting intention",
                    intentionEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Intention found",
                    "Intention found",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "intention", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Intention found"
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
        @RequestParam Map<String, String> intentionRequestParam
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("intention", intentionRequestParam),
            "Searching intention"
        );
        String id = intentionRequestParam.get("id");
        String name = intentionRequestParam.get("name");
        String description = intentionRequestParam.get("description");
        String contextId = intentionRequestParam.get("contextId");
        var intention = Intention.of(
            id != null ? UUID.fromString(id) : null,
            name,
            description,
            contextId != null ? UUID.fromString(contextId) : null
        );
        var intentionDT = Transformer.transform(intention, IntentionDTAssembler::new);
        Either<ServiceError, Pageable<IntentionDT>> intentionEither = intentionService.search(intentionDT, page, size, sortFields, sortDirection);
        return intentionEither.fold(
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
                            "intention", intentionRequestParam
                        )
                    ),
                    "Error searching intention",
                    intentionEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseDataPaginated(
                    "200",
                    "Intention found",
                    "Intention found",
                    right.getItems(),
                    right.getTotal(),
                    right.getPageTotal(),
                    right.getPageNumber(),
                    right.getPageSize()
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "intention", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Intention found"
                );
                MDC.clear();
                return ResponseEntity.ok(response);
            }
        );
    }
}
