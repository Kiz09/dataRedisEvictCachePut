package com.kiz.dataRedis.repository;

import com.kiz.dataRedis.handleErrors.CustomCheckedException;
import java.util.List;
import java.util.Optional;
import com.kiz.dataRedis.model.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public static final String HASH_KEY = "Product";
    private final RedisTemplate template;

    public ProductRepository(RedisTemplate template) {
        this.template = template;
    }

    public Product save(Product product) throws CustomCheckedException {

        try {
            template.opsForHash().put(HASH_KEY, product.getId(), product);
            return product;
        } catch (RuntimeException e) {
            throw new CustomCheckedException("Error saving value from hash", e);
        }

    }

    public List<Product> findAll() throws CustomCheckedException {

        try {
            return template.opsForHash().values(HASH_KEY);
        } catch (RuntimeException e) {
            throw new CustomCheckedException("Error deleting value from hash", e);
        }


    }

    public Product findById(int id){

        return Optional.ofNullable((Product) template.opsForHash().get(HASH_KEY, id))
                .orElseThrow(() -> new RuntimeException("Value is null"));

    }



    @SuppressWarnings("unchecked")
    public String deleteProduct(int id) throws CustomCheckedException {
        try {
            template.opsForHash().delete(HASH_KEY, String.valueOf(id));
            return "Deleted";
        } catch (RuntimeException e) {
            throw new CustomCheckedException("Error deleting value from hash", e);
        }
    }

}
