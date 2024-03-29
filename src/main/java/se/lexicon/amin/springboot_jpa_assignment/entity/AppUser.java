package se.lexicon.amin.springboot_jpa_assignment.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private List<ProductOrder> productOrders;

    public AppUser() {
    }

    public AppUser(int id, String firstName, String lastName, String email, List<ProductOrder> productOrders) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setProductOrders(productOrders);
    }

    public AppUser(String firstName, String lastName, String email) {
        this(0, firstName, lastName, email, null);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public void addProductOrder(ProductOrder productOrder) {
        if (productOrders == null) {
            productOrders = new ArrayList<>();
        }

        if(productOrder == null) {
            throw new IllegalArgumentException("Product order is null");
        }

        if(!productOrders.contains(productOrder)) {

            productOrders.add(productOrder);
            productOrder.setCustomer(this);
        }
    }

    public void removeProductOrder(ProductOrder productOrder) {
        if(productOrders != null) {
            if(productOrder == null) {
                throw new IllegalArgumentException("Product order is null");
            }
            productOrders.remove(productOrder);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(email, appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
