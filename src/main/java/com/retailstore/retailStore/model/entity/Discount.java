package com.retailstore.retailStore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retailstore.retailStore.model.entity.auditing.AuditEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "discounts", schema = "public")
public class Discount extends AuditEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "value", nullable = false)
    private int value;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "discount"
    )
    private Set<User> users;

    public Discount() {
    }

    public Discount(int value) {
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return value == discount.value &&
                Objects.equals(id, discount.id) &&
                Objects.equals(users, discount.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
