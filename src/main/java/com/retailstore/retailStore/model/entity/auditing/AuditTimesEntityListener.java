package com.retailstore.retailStore.model.entity.auditing;


import org.springframework.web.context.request.RequestContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.util.Date;

public class AuditTimesEntityListener {
    @PrePersist
    public void onPersist(AuditEntity auditEntity) {
        auditEntity.setCreatedDate(Date.from(Instant.now()));
        try {
            final var requestAttributes = RequestContextHolder.getRequestAttributes();
            // TODO implement later
            final String id = "";
            auditEntity.setCreatedBy(id);
        } catch (Exception ignored) {
        }
    }

    @PreUpdate
    public void onUpdate(AuditEntity auditEntity) {
        auditEntity.setLastModifiedDate(Date.from(Instant.now()));
        try {
            final var requestAttributes = RequestContextHolder.getRequestAttributes();
            // TODO implement later
            final String id = "";
            auditEntity.setLastModifiedBy(id);
        } catch (Exception ignored) {
        }
    }
}
