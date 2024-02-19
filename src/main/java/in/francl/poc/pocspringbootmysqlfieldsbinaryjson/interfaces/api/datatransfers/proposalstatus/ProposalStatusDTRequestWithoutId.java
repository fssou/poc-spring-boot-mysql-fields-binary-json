package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.proposalstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.proposalstatus.ProposalStatusWithoutIdDT;

@JsonPropertyOrder
public class ProposalStatusDTRequestWithoutId implements ProposalStatusWithoutIdDT {

    private final String name;
    private final String description;

    private ProposalStatusDTRequestWithoutId(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonCreator
    public static ProposalStatusDTRequestWithoutId of(
        @JsonProperty(value = "name") String name,
        @JsonProperty(value = "description") String description
    ) {
        return new ProposalStatusDTRequestWithoutId(name, description);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
