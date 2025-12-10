package com.org.phasezero_catalog_service.service;

import com.org.phasezero_catalog_service.entity.Product;
import com.org.phasezero_catalog_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.org.phasezero_catalog_service.exception.ProductAlreadyExistsException;


import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpe implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpe(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {

        productRepository.findByPartNumber(product.getPartNumber())
                .ifPresent(p -> {
                    throw new ProductAlreadyExistsException(
                            "Product with partNumber already exists: " + product.getPartNumber()
                    );
                });

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock must be non-negative");
        }

        return productRepository.save(product);
    }

    // Get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Search by part name
    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByPartNameContainingIgnoreCase(name);
    }

    // Filter by category
    @Override
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    public List<Product> sortByPriceAsc() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .toList();
    }

    @Override
    public double getTotalInventoryValue() {
        return productRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
}