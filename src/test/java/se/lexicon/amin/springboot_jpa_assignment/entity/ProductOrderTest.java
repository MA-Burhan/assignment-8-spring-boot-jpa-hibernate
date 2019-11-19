package se.lexicon.amin.springboot_jpa_assignment.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductOrderTest {

    OrderItem orderItem;
    ProductOrder productOrder;
    Product product;

    @BeforeEach
    public void setup() {
        productOrder = productOrder = new ProductOrder(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
        product = new Product("Coca-Cola", 15.9);
        orderItem = new OrderItem(2);
        orderItem.setProduct(product);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);

        productOrder.setOrderItems(orderItemList);
    }

    @Test
    public void create_productOrder(){

        LocalDateTime orderDate = LocalDateTime.of(2019, 1, 1, 0, 0, 0);

        assertEquals(0, productOrder.getId());
        assertEquals(orderDate, productOrder.getOrderDateTime());
        assertEquals(orderItem, productOrder.getOrderItems().get(0));
        assertNull(productOrder.getCustomer());
    }

    @Test
    public void productOrder_equals_hashcode(){
        ProductOrder productOrder_copy = new ProductOrder();
        productOrder_copy.setOrderDateTime(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
        assertTrue(productOrder.equals(productOrder_copy));
        assertEquals(productOrder.hashCode(), productOrder_copy.hashCode());
    }

    @Test
    public void productOrder_toString(){
        String toString = productOrder.toString();
        assertTrue(toString.contains("id=0") &&
                toString.contains("orderDateTime=2019-01-01T00:00") &&
                toString.contains("customer=null"));
    }

    @Test
    public void addOrderItem() {
        OrderItem newOrderItem= new OrderItem(1);
        newOrderItem.setProduct(product);

        productOrder.addOrderItem(newOrderItem);

        OrderItem retrievedOrderItem = productOrder.getOrderItems().get(1);

        assertEquals(newOrderItem, retrievedOrderItem);
        assertEquals(productOrder, retrievedOrderItem.getProductOrder());
    }

    @Test
    public void addOrderItem_ifNullThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> productOrder.addOrderItem(null));
    }

    @Test
    public void removeOrderItem() {
        assertTrue(productOrder.getOrderItems().contains(orderItem));

        productOrder.removeOrderItem(orderItem);

        assertFalse(productOrder.getOrderItems().contains(orderItem));
        assertTrue(productOrder.getOrderItems().isEmpty());
    }

    @Test
    public void removeOrderItem_ifNullThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> productOrder.removeOrderItem(null));
    }

    @Test
    public void calculateTotalPrice() {
        OrderItem newOrderItem = new OrderItem(1);
        newOrderItem.setProduct(product);
        productOrder.addOrderItem(newOrderItem);

        assertEquals(47.7, productOrder.calculateTotalPrice());


    }
}
