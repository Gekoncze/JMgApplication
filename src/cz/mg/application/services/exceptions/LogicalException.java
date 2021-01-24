package cz.mg.application.services.exceptions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.MgEntity;


public class LogicalException extends RuntimeException {
    @Optional @Value
    private final Long entityId;

    public LogicalException(MgEntity entity, String message) {
        this(entity.getId(), message);
    }

    public LogicalException(Long entityId, String message) {
        super(message);
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }
}
