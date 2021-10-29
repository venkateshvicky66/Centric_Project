package com.product.project.repository;

import com.product.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Jpa repository for accessing the database and perform custom queries
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>
{
	List<Product> findByCategory(String category);
}
