package com.org.phasezero_catalog_service.controller;

import com.org.phasezero_catalog_service.entity.Product;
import com.org.phasezero_catalog_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1) Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED); // 201
    }

    // 2) Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 3) Search by partName 
    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam("name") String name) {
        return productService.searchByName(name);
    }

    // 4) Filter by category 
    @GetMapping("/filter")
    public List<Product> filterByCategory(@RequestParam("category") String category) {
        return productService.filterByCategory(category);
    }

    // 5) Sort by price ascending
    @GetMapping("/sort/price")
    public List<Product> sortByPriceAsc() {
        return productService.sortByPriceAsc();
    }

    @GetMapping("/inventory/value")
    public double getTotalInventoryValue() {
        return productService.getTotalInventoryValue();
    }
}
