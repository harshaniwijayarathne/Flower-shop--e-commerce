package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.product;
import com.example.demo.repo.ProductRepository;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {
     
	private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // 1. Read All Products (GET)
    @GetMapping
    public List<product> getAllProducts() {
        return repository.findAll();
    }

    // 2. Create Product (POST)
    @PostMapping
    public product addProduct(@RequestBody product product) {
        return repository.save(product);
    }

    // 3. Update Product (PUT) 
    @PutMapping("/{id}")
    public product updateProduct(@PathVariable Long id, @RequestBody product details) {
        product p = repository.findById(id).orElseThrow();
        p.setName(details.getName());
        p.setPrice(details.getPrice());
        p.setStock(details.getStock());
        return repository.save(p);
    }

    // 4. Delete Product (DELETE)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
