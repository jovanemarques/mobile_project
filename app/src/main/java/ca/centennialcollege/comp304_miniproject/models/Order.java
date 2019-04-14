package ca.centennialcollege.comp304_miniproject.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int number;
    private Client user;
    private List<Product> products = new ArrayList<>();
    private Address deliveryAddress;
    private OrderStatus status;
    private String statusReason;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return user;
    }

    public void setClient(Client user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
