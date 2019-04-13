package ca.centennialcollege.comp304_miniproject.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class DataRepository {

    private static DataRepository dataRepository;
    private static Dictionary<Integer, Client> clients = new Hashtable<>();
    private static List<Order> orders = new ArrayList<>();
    private static List<Deliverer> deliverers = new ArrayList<>();

    public static void SeedData() {

        populateClients();

        populateOrders();

//        Product product1 = new Product();
//        product1.setId(1);
//        product1.setName("Smartphone");
//
//        products.add(product1);
//
//        Product product2 = new Product();
//        product2.setId(2);
//        product2.setName("Laptop");
//
//        products.add(product2);

    }

    private static void populateClients() {
        Client client = new Client();
        client.setId(1);
        client.setName("Eduardo");

        clients.put(client.getId(), client);

        client = new Client();
        client.setId(2);
        client.setName("Jovane");

        clients.put(client.getId(), client);

        client = new Client();
        client.setId(3);
        client.setName("Aesha");

        clients.put(client.getId(), client);

        client = new Client();
        client.setId(4);
        client.setName("Ailton");

        clients.put(client.getId(), client);
    }

    private static void populateOrders() {
        Order order = new Order();
        order.setId(1);
        order.setNumber(123456);
        order.setClient(clients.get(1));
        order.addProduct(new Product("", 0.0f));
        order.addProduct(new Product("", 0.0f));

        orders.add(order);
    }



    public static Enumeration<Client> getClients() {
        return clients.elements();
    }

    public static void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Deliverer> getDeliverers() {
        return deliverers;
    }
}
