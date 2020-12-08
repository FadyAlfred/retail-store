package com.retailstore.retailStore.model.entity;

import com.retailstore.retailStore.model.entity.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "categories", schema = "public")
public class Category extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "discount_allowed", nullable = false)
    private boolean discountAllowed;

    public Category() {
    }

    public Category(@NotNull @NotBlank String name, String slug, boolean discountAllowed) {
        this.name = name;
        this.slug = slug;
        this.discountAllowed = discountAllowed;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public boolean isDiscountAllowed() {
        return discountAllowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return discountAllowed == category.discountAllowed &&
                Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(slug, category.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
