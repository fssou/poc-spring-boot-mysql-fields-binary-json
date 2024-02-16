package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.contracts;

import java.util.List;

public interface Pageable<T> {

    List<T> getItems();

    long getTotal();

    long getPageNumber();

    long getPageTotal();

    long getPageSize();


}
