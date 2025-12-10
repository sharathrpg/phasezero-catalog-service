package com.org.phasezero_catalog_service.service;

import com.org.phasezero_catalog_service.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    List<Product> searchByName(String name);

    List<Product> filterByCategory(String category);

    List<Product> sortByPriceAsc();

    double getTotalInventoryValue();
}
