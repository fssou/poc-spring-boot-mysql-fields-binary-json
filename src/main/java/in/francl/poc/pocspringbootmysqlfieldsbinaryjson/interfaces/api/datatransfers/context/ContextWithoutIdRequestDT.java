package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.context;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context.ContextWithoutIdDT;

@JsonPropertyOrder
public final class ContextWithoutIdRequestDT implements ContextWithoutIdDT {

    private final String name;
    private final String description;

    private ContextWithoutIdRequestDT(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonCreator
    public static ContextWithoutIdRequestDT of(
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "description")
        String description
    ) {
        return new ContextWithoutIdRequestDT(name, description);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
