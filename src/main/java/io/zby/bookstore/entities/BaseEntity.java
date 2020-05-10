package io.zby.bookstore.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    protected Long id;
    @CreatedDate
    protected Date createAt;
    @LastModifiedDate
    protected Date updateAt;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateAt() {
        return createAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
