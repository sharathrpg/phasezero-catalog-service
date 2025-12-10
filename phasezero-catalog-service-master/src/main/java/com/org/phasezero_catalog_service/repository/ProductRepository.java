package com.org.phasezero_catalog_service.repository;

import com.org.phasezero_catalog_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByPartNumber(String partNumber);

    List<Product> findByPartNameContainingIgnoreCase(String partName);

    List<Product> findByCategoryIgnoreCase(String category);
}