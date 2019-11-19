package se.lexicon.amin.springboot_jpa_assignment.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class OrderItemTest {

    OrderItem orderItem;
    ProductOrder productOrder;
    Product product;

    @BeforeEach
    public void setup(){
        productOrder = new ProductOrder(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
        product = new Product("Coca-Cola", 15.9);
        orderItem = new OrderItem(2);
        orderItem.setProduct(product);
        orderItem.setProductOrder(productOrder);
    }

    @Test
    public void create_orderItem(){

        assertEquals(0, orderItem.getId());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(product, orderItem.getProduct());
        assertEquals(productOrder, orderItem.getProductOrder());
    }

    @Test
    public void orderItem_equals_hashcode_true(){
        OrderItem orderItem_copy = new OrderItem();
        orderItem_copy.setQuantity(2);
        orderItem_copy.setProduct(product);
        orderItem_copy.setProductOrder(productOrder);
        orderItem_copy.setProductOrder(productOrder);
        assertTrue(orderItem.equals(orderItem_copy));
        assertEquals(orderItem.hashCode(), orderItem_copy.hashCode());
    }


    @Test
    public void orderItem_toString(){
        String toString = orderItem.toString();
        assertTrue(toString.contains("id=0") &&
                toString.contains("quantity=2") &&
                toString.contains("Product{id=0, name='Coca-Cola', price=15.9}") &&
                toString.contains("ProductOrder{id=0, orderDateTime=2019-01-01T00:00, customer=null}"));
    }

    @Test
    public void calculatePrice() {

        assertEquals(31.8, orderItem.calculatePrice());
    }
}
