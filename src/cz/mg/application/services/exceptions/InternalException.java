package cz.mg.application.services.exceptions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.MgStaticalEntity;


public class InternalException extends RuntimeException {
    @Optional @Value
    private final Long entityId;

    public InternalException(String message) {
        this((Long)null, message);
    }

    public InternalException(MgStaticalEntity entity, String message) {
        this(entity.getId(), message);
    }

    public InternalException(Long entityId, String message) {
        super(message);
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }
}
