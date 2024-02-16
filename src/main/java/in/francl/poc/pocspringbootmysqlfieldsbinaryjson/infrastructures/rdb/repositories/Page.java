package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts.Pageable;

import java.util.List;

public class Page<T> implements Pageable<T> {

    private final List<T> items;
    private final long total;
    private final long pageNumber;
    private final long pageSize;
    private final long pageTotal;


    private Page(
        List<T> items,
        long total,
        long pageNumber,
        long pageTotal,
        long pageSize
    ) {
        this.items = items;
        this.total = total;
        this.pageNumber = pageNumber;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
    }

    public static <T> Page<T> of(
        List<T> items,
        long total,
        long pageNumber,
        long pageTotal,
        long pageSize
    ) {
        return new Page<>(items, total, pageNumber, pageTotal, pageSize);
    }

    @Override
    public List<T> getItems() {
        return this.items;
    }


    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public long getPageNumber() {
        return pageNumber;
    }

    @Override
    public long getPageSize() {
        return pageSize;
    }

    @Override
    public long getPageTotal() {
        return pageTotal;
    }
}
