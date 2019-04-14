package ca.centennialcollege.comp304_miniproject.models;

import java.util.List;

public class Deliverer {
    private String name;

    private List<Order> ordersAssigned;

    private float currentLat;
    private float currentLng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrdersAssigned() {
        return ordersAssigned;
    }

    public void setOrdersAssigned(List<Order> ordersAssigned) {
        this.ordersAssigned = ordersAssigned;
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
