package com.retailstore.retailStore.model.entity.auditing;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditTimesEntityListener.class)
public class AuditEntity {

    @JsonIgnore
    @Column(name = "created_by", insertable = true, updatable = false)
    private String createdBy;

    @JsonIgnore
    @Column(name = "created_date", insertable = true, updatable = false)
    private Date createdDate;

    @JsonIgnore
    @Column(name = "last_modified_by", insertable = false, updatable = true)
    private String lastModifiedBy;

    @JsonIgnore
    @Column(name = "last_modified_date", insertable = false, updatable = true)
    private Date lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
