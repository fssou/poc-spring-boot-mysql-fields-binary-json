package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.datatransfers.intention;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.datatransfers.intention.IntentionWithoutIdDT;

import java.util.UUID;

public class IntentionWithoutIdDTImpl implements IntentionWithoutIdDT {

        private final String name;
        private final String description;
        private final UUID contextId;

        protected IntentionWithoutIdDTImpl(String name, String description, UUID contextId) {
            this.name = name;
            this.description = description;
            this.contextId = contextId;
        }

        public static IntentionWithoutIdDT of(String name, String description, UUID contextId) {
            return new IntentionWithoutIdDTImpl(name, description, contextId);
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getDescription() {
            return this.description;
        }

        @Override
        public UUID getContextId() {
            return this.contextId;
        }
}
