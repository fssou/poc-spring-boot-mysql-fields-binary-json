package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.v1;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.errors.ServiceError;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.applications.service.ContextService;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextDT;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.Context;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either.Either;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.assemblers.context.ContextDTAssembler;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.controllers.BaseController;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.context.ContextWithoutIdRequestDT;
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
@RequestMapping("/v1/contexts")
public class ContextController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContextController.class);

    private final ContextService contextService;

    public ContextController(ContextService contextService) {
        this.contextService = contextService;
    }

    @PostMapping(
        consumes = "application/json",
        produces = "application/json"
    )
    public ResponseEntity<?> create(
        @RequestHeader("X-Correlation-ID") String correlationId,
        @RequestBody ContextWithoutIdRequestDT contextDT
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("context", contextDT),
            "Creating context"
        );
        Either<ServiceError, ContextDT> contextCreatedEither = contextService.createAndSave(contextDT);
        if (contextCreatedEither.isLeft()) {
            var left = contextCreatedEither.getLeft();
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
                        "context", contextDT
                    )
                ),
                "Error creating context",
                contextCreatedEither.getLeft()
            );
            return ResponseEntity.badRequest().body(response);
        }
        var response = Response.withResponseData(
            "201",
            "Context created",
            "Context created",
            contextCreatedEither.getRight()
        );
        LOGGER.info(
            Markers.appendEntries(
                Map.of(
                    "context", contextCreatedEither.getRight(),
                    "duration", timer.stop().toMillis()
                )
            ),
            "Context created"
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
            Markers.append("contextID", id),
            "Deleting context"
        );
        Either<ServiceError, ContextDT> contextEither = contextService.delete(id);
        return contextEither.fold(
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
                            "contextId", id
                        )
                    ),
                    "Error deleting context",
                    contextEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Context deleted",
                    "Context deleted",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "context", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Context deleted"
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
            Markers.append("contextID", id),
            "Getting context"
        );
        Either<ServiceError, ContextDT> contextEither = contextService.get(id);
        return contextEither.fold(
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
                            "contextId", id
                        )
                    ),
                    "Error getting context",
                    contextEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseData(
                    "200",
                    "Context found",
                    "Context found",
                    right
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "context", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Context found"
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
        @RequestParam() Map<String, String> contextRequestParam
    ) {
        var timer = Timer.start();
        MDC.put("correlationId", correlationId);
        LOGGER.info(
            Markers.append("context", contextRequestParam),
            "Searching context"
        );
        String id = contextRequestParam.get("id");
        String name = contextRequestParam.get("name");
        String description = contextRequestParam.get("description");
        var context = Context.of(
            id != null ? UUID.fromString(id) : null,
            name,
            description
        );
        var contextDT = Transformer.transform(context, ContextDTAssembler::new);
        Either<ServiceError, Pageable<ContextDT>> contextEither = contextService.search(contextDT, page, size, sortFields, sortDirection);
        return contextEither.fold(
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
                            "context", contextRequestParam
                        )
                    ),
                    "Error searching context",
                    contextEither.getLeft()
                );
                MDC.clear();
                return ResponseEntity.badRequest().body(response);
            },
            right -> {
                var response = Response.withResponseDataPaginated(
                    "200",
                    "Context found",
                    "Context found",
                    right.getItems(),
                    right.getTotal(),
                    right.getPageTotal(),
                    right.getPageNumber(),
                    right.getPageSize()
                );
                LOGGER.info(
                    Markers.appendEntries(
                        Map.of(
                            "context", right,
                            "duration", timer.stop().toMillis()
                        )
                    ),
                    "Context found"
                );
                MDC.clear();
                return ResponseEntity.ok(response);
            }
        );
    }
}
