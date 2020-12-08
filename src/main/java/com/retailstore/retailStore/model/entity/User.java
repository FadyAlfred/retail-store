package com.retailstore.retailStore.model.entity;

import com.retailstore.retailStore.model.entity.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
public class User extends AuditEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @NotNull
    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "is_employee")
    private boolean isEmployee;

    @Column(name = "is_affiliate")
    private boolean isAffiliate;

    @ManyToOne
    private Discount discount;

    public User() {
    }

    public User(String id, @NotBlank @NotNull String name, boolean isEmployee, boolean isAffiliate, Discount discount) {
        this.id = UUID.fromString(id);
        Name = name;
        this.isEmployee = isEmployee;
        this.isAffiliate = isAffiliate;
        this.discount = discount;
    }

    public User(String id, @NotBlank @NotNull String name, boolean isEmployee, boolean isAffiliate) {
        this.id = UUID.fromString(id);
        Name = name;
        this.isEmployee = isEmployee;
        this.isAffiliate = isAffiliate;
    }


    public String getId() {
        return id.toString();
    }

    public String getName() {
        return Name;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public boolean isAffiliate() {
        return isAffiliate;
    }

    public Discount getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isEmployee == user.isEmployee &&
                isAffiliate == user.isAffiliate &&
                Objects.equals(id, user.id) &&
                Objects.equals(Name, user.Name) &&
                Objects.equals(discount, user.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

