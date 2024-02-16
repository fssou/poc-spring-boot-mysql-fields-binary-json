package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.intention;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionWithoutIdDT;

import java.util.UUID;

@JsonPropertyOrder
public class IntentionDTRequestWithoutId implements IntentionWithoutIdDT {

    private final String name;
    private final String description;
    private final UUID contextId;

    private IntentionDTRequestWithoutId(String name, String description, UUID contextId) {
        this.name = name;
        this.description = description;
        this.contextId = contextId;
    }

    @JsonCreator
    public static IntentionDTRequestWithoutId of(
        @JsonProperty(value = "name") String name,
        @JsonProperty(value = "description") String description,
        @JsonProperty(value = "contextId") UUID contextId
    ) {
        return new IntentionDTRequestWithoutId(name, description, contextId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public UUID getContextId() {
        return contextId;
    }
}
