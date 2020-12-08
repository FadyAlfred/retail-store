package com.retailstore.retailStore.dao;

import com.retailstore.retailStore.model.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscountDao extends JpaRepository<Discount, UUID> {
}
