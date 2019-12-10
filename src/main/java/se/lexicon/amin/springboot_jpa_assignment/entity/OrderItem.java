package se.lexicon.amin.springboot_jpa_assignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne(cascade={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(cascade={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name="product_order_id")
    private ProductOrder productOrder;

    public OrderItem() {
    }

    public OrderItem(int id, int quantity, Product product, ProductOrder productOrder) {
        this.id = id;
        setQuantity(quantity);
        setProduct(product);
        setProductOrder(productOrder);
    }

    public OrderItem(int quantity) {
        this(0, quantity, null, null);
    }


    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }


    public double calculatePrice() {

        return product.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                quantity == orderItem.quantity &&
                Objects.equals(product, orderItem.product) &&
                Objects.equals(productOrder, orderItem.productOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product, productOrder);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append(", product=").append(product);
        sb.append(", productOrder=").append(productOrder);
        sb.append('}');
        return sb.toString();
    }
}
