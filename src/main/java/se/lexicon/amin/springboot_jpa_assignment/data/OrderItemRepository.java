package se.lexicon.amin.springboot_jpa_assignment.data;

import se.lexicon.amin.springboot_jpa_assignment.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {
    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findById(int id);

    List<OrderItem> findAll();

    void delete(int id);
}
