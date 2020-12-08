package com.retailstore.retailStore.dao;

import com.retailstore.retailStore.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<Product, UUID> {
}
