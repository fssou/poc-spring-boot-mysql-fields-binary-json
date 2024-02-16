package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.interfaces.api.datatransfers.response;

/**
 *
 */
public class ResponseDataPaginatedPagePartial {
    private final long total;
    private final long number;
    private final long size;

    protected ResponseDataPaginatedPagePartial(long total, long number, long size) {
        this.total = total;
        this.number = number;
        this.size = size;
    }

    public static ResponseDataPaginatedPagePartial of(long total, long number, long size) {
        return new ResponseDataPaginatedPagePartial(total, number, size);
    }

    public long getTotal() {
        return total;
    }

    public long getOffset() {
        return number;
    }

    public long getSize() {
        return size;
    }

}
