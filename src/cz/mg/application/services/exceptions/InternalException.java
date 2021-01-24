package cz.mg.application.services.exceptions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.MgEntity;


public class InternalException extends RuntimeException {
    @Optional @Value
    private final long entityId;

    public InternalException(String message) {
        this(-1L, message);
    }

    public InternalException(MgEntity entity, String message) {
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
