package se.lexicon.amin.springboot_jpa_assignment.data;

import se.lexicon.amin.springboot_jpa_assignment.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(int id);

    List<Product> findByName(String name);

    List<Product> findAll();

    void delete(int id);
}
