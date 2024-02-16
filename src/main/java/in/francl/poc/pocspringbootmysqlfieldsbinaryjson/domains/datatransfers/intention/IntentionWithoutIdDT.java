package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention;

import java.util.UUID;

public interface IntentionWithoutIdDT {
    String getName();
    String getDescription();
    UUID getContextId();
}
