package com.retailstore.retailStore.dao;

import com.retailstore.retailStore.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryDao extends JpaRepository<Category, UUID> {
}
