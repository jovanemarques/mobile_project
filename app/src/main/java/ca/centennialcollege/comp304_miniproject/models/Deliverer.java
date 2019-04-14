package ca.centennialcollege.comp304_miniproject.models;

import java.util.ArrayList;
import java.util.List;

public class Deliverer {
    private int id;
    private String name;

    private List<Order> ordersAssigned;

    private float currentLat;
    private float currentLng;

    public Deliverer() {
        ordersAssigned = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrdersAssigned() {
        return ordersAssigned;
    }

    public void addOrder(Order order) {
        this.ordersAssigned.add(order);
    }

    public float getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(float currentLat) {
        this.currentLat = currentLat;
    }

    public float getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(float currentLng) {
        this.currentLng = currentLng;
    }
}
