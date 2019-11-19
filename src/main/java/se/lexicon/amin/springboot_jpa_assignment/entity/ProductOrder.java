package se.lexicon.amin.springboot_jpa_assignment.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ProductOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private LocalDateTime orderDateTime;

    @OneToMany(mappedBy="productOrder", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;


    @ManyToOne(cascade={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name="app_user_id")
    private AppUser customer;

    public ProductOrder() {
    }

    public ProductOrder(LocalDateTime orderDateTime) {
        setOrderDateTime(orderDateTime);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }

        if(orderItem == null) {
            throw new IllegalArgumentException("Order item is null");
        }

        if(!orderItems.contains(orderItem)) {

            orderItems.add(orderItem);
            orderItem.setProductOrder(this);
        }

    }

    public void removeOrderItem(OrderItem orderItem){

        if(orderItems != null) {
            if(orderItem == null) {
                throw new IllegalArgumentException("Order item is null");
            }
            orderItems.remove(orderItem);
        }
    }

    public double calculateTotalPrice() {
        double sum = 0;
        if (orderItems != null) {
            for(OrderItem ot : orderItems) {
                sum += ot.calculatePrice();
            }
        }
        return sum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return id == that.id &&
                Objects.equals(orderDateTime, that.orderDateTime) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDateTime, customer);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductOrder{");
        sb.append("id=").append(id);
        sb.append(", orderDateTime=").append(orderDateTime);
        sb.append(", customer=").append(customer);
        sb.append('}');
        return sb.toString();
    }
}
