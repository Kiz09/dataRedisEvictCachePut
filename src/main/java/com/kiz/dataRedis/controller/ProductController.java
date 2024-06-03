package com.kiz.dataRedis.controller;

import com.kiz.dataRedis.handleErrors.CustomCheckedException;
import java.util.List;
import com.kiz.dataRedis.model.Product;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kiz.dataRedis.repository.ProductRepository;

@RestController
@RequestMapping("/product/")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product product) throws CustomCheckedException {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() throws CustomCheckedException {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id) {
        return productRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws CustomCheckedException {
        return productRepository.deleteProduct(id);
    }


}
