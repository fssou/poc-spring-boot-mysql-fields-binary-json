package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.events;

import in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.entities.contracts.Entity;

public class DomainResult<En extends Entity, Ev extends DomainEvent> {
    private final En entity;
    private final Ev event;

    private DomainResult(En entity, Ev event) {
        this.entity = entity;
        this.event = event;
    }

    public static <En extends Entity, Ev extends DomainEvent> DomainResult<En, Ev> of(En entity, Ev event) {
        return new DomainResult<>(entity, event);
    }

    public En getEntity() {
        return this.entity;
    }

    public Ev getEvent() {
        return this.event;
    }
}
