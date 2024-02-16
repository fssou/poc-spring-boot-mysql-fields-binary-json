package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData<D> extends ResponseBase {

    private D data;


    protected ResponseData(
        String code,
        String message,
        String details,
        D data
    ) {
        super(code, message, details);
        this.data = data;
    }

    public D getData() {
        return data;
    }

    @JsonCreator
    public static <D> ResponseData<D> of(
        @JsonProperty("code")
        String code,
        @JsonProperty("message")
        String message,
        @JsonProperty("details")
        String details,
        @JsonProperty("data")
        D data
    ) {
        return new ResponseData<>(code, message, details, data);
    }

}
