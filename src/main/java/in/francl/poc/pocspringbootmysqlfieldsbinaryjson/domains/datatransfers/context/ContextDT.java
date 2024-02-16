package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.context;

import java.util.UUID;

public interface ContextDT extends ContextWithoutIdDT {
    UUID getId();
}
