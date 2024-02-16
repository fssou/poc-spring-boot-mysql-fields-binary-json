package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.errors.Error;

import java.util.List;

public class Response {
    private ResponseData<?> response;
    private ResponseError error;
    private ResponseDataPaginated<?> paginated;

    public static <D> ResponseData<D> withResponseData(String code, String message, String details, D data) {
        return ResponseData.of(code, message, details, data);
    }

    public static ResponseError withResponseError(String code, String message, String details, List<Error> errors) {
        return ResponseError.of(code, message, details, errors);
    }

    public static <D> ResponseDataPaginated<D> withResponseDataPaginated(
        String code,
        String message,
        String details,
        List<D> items,
        long total,
        long pageTotal,
        long pageNumber,
        long pageSize
    ) {
        var page = ResponseDataPaginatedPagePartial.of(pageTotal, pageNumber, pageSize);
        var paginatedData = ResponseDataPaginatedPartial.of(items, total, page);
        return ResponseDataPaginated.of(code, message, details, paginatedData);
    }

}
