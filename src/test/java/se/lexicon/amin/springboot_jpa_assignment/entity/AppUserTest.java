package se.lexicon.amin.springboot_jpa_assignment.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppUserTest {

    AppUser appUser;
    ProductOrder productOrder;

    @BeforeEach
    public void setup() {
        appUser = new AppUser("John", "Doe", "john.doe@email.com");
        productOrder = new ProductOrder(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
        productOrder.setCustomer(appUser);
        List<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(productOrder);
        appUser.setProductOrders(productOrderList);


    }
    @Test
    public void create_appUser(){

        assertEquals(0, appUser.getId());
        assertEquals("John", appUser.getFirstName());
        assertEquals("Doe", appUser.getLastName());
        assertEquals("john.doe@email.com", appUser.getEmail());
    }

    @Test
    public void appUser_equals_hashcode(){
        AppUser appUser_copy = new AppUser();
        appUser_copy.setFirstName("John");
        appUser_copy.setLastName("Doe");
        appUser_copy.setEmail("john.doe@email.com");
        assertTrue(appUser.equals(appUser_copy));
        assertEquals(appUser.hashCode(), appUser_copy.hashCode());
    }

    @Test
    public void appUser_toString(){
        String toString = appUser.toString();
        assertTrue(toString.contains("id=0") &&
                        toString.contains("firstName='John'") &&
                        toString.contains("lastName='Doe'") &&
                        toString.contains("email='john.doe@email.com'")
        );
    }

    @Test
    public void addProductOrder(){
        LocalDateTime orderDate = LocalDateTime.of(2019, 11, 18, 0, 0, 0);
        ProductOrder productOrder = new ProductOrder(orderDate);
        appUser.addProductOrder(productOrder);

        ProductOrder retrievedProductOrder = appUser.getProductOrders().get(1);

        assertEquals(0, retrievedProductOrder.getId());
        assertEquals(orderDate, retrievedProductOrder.getOrderDateTime());
        assertEquals(appUser, retrievedProductOrder.getCustomer());
        assertNull(retrievedProductOrder.getOrderItems());
    }

    @Test
    public void addProductOrder_ifNullThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> appUser.addProductOrder(null));
    }
    @Test
    public void removeProductOrder(){

        assertTrue(appUser.getProductOrders().contains(productOrder));

        appUser.removeProductOrder(productOrder);

        assertFalse(appUser.getProductOrders().contains(productOrder));
        assertTrue(appUser.getProductOrders().isEmpty());
    }

    @Test
    public void removeProductOrder_ifNullThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> appUser.removeProductOrder(null));
    }


}
