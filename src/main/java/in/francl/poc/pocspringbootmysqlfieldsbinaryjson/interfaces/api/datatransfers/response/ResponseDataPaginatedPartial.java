package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response;

import java.util.List;

public class ResponseDataPaginatedPartial<I> {
    private final List<I> items;
    private final long total;
    private final ResponseDataPaginatedPagePartial page;


    protected ResponseDataPaginatedPartial(List<I> items, long total, ResponseDataPaginatedPagePartial page) {
        this.items = items;
        this.total = total;
        this.page = page;
    }

    public static <I> ResponseDataPaginatedPartial<I> of(List<I> items, long total, ResponseDataPaginatedPagePartial page) {
        return new ResponseDataPaginatedPartial<>(items, total, page);
    }

    public List<I> getItems() {
        return items;
    }

    public long getTotal() {
        return total;
    }

    public ResponseDataPaginatedPagePartial getPage() {
        return page;
    }
}
