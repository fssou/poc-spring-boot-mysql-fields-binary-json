package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response;

public class ResponseDataPaginated<D> extends ResponseData<ResponseDataPaginatedPartial<D>> {

    protected ResponseDataPaginated(String code, String message, String details, ResponseDataPaginatedPartial<D> data) {
        super(code, message, details, data);
    }

    public static <D> ResponseDataPaginated<D> of(String code, String message, String details, ResponseDataPaginatedPartial<D> data) {
        return new ResponseDataPaginated<D>(code, message, details, data);
    }

}
