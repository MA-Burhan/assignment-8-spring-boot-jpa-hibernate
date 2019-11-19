package se.lexicon.amin.springboot_jpa_assignment;

import se.lexicon.amin.springboot_jpa_assignment.entity.AppUser;
import se.lexicon.amin.springboot_jpa_assignment.entity.OrderItem;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;
import se.lexicon.amin.springboot_jpa_assignment.entity.ProductOrder;

import java.time.LocalDateTime;
import java.util.Objects;

public class test {



    public static void main(String[] args) {
        ProductOrder productOrder = new ProductOrder(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
        Product product = new Product("Coca-Cola", 15.9);
        OrderItem orderItem = new OrderItem(1, product, productOrder);
        AppUser appUser = new AppUser("John", "Doe", "john.doe@email.com");
        System.out.println(orderItem);

        System.out.println(product);
        System.out.println(appUser);
        System.out.println(productOrder);

        System.out.println(Objects.equals(null, null));

    }
}
