package se.lexicon.amin.springboot_jpa_assignment.data;

import se.lexicon.amin.springboot_jpa_assignment.entity.ProductOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductOrderRepository {
    ProductOrder save(ProductOrder productOrder);

    Optional<ProductOrder> findById(int id);

    List<ProductOrder> findByDate(LocalDateTime orderDate);

    List<ProductOrder> findByAppUserId(int appUserId);

    List<ProductOrder> findByProductId(int productId);

    List<ProductOrder> findByProductName(String productName);

    List<ProductOrder> findAll();

    void delete(int id);
}
