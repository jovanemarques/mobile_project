package ca.centennialcollege.comp304_miniproject.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class DataRepository {

    private static DataRepository dataRepository;
    private static Dictionary<Integer, Client> clients = new Hashtable<>();
    private static Dictionary<Integer, Order> orders = new Hashtable<>();
    private static Dictionary<Integer, Deliverer> deliverers = new Hashtable<>();

    public static void SeedData() {

        populateClients();

        populateOrders();

        populateDeliverers();
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
        order.addProduct(new Product("Pro ASP.NET Core MVC 2", 47.42f));
        order.addProduct(new Product("Bluetooth Headphones, Parasom A1 Magnetic", 26.99f));
        order.setStatus(OrderStatus.RECEIVED);
        order.setDeliveryAddress(new Address(826, "Huntingwood Dr", "",
                "Scarborough", "ON", "M1T 2L6", 43.794348f, -79.296368f));

        orders.put(order.getId(), order);

        order = new Order();
        order.setId(2);
        order.setNumber(234567);
        order.setClient(clients.get(2));
        order.addProduct(new Product("Echo Dot (3rd gen) - Smart speaker with Alexa - Charcoal", 69.99f));
        order.setStatus(OrderStatus.RECEIVED);
        order.setDeliveryAddress(new Address(3760, "Sheppard Ave E", "Apt. 224",
                "Scarborough", "ON", "M1T 3K9", 43.781942f, -79.294326f));

        orders.put(order.getId(), order);

        order = new Order();
        order.setId(3);
        order.setNumber(345875);
        order.setClient(clients.get(3));
        order.addProduct(new Product("Cuisinart DGB-900BCC Automatic Burr Grind & Brew Thermal TM 12 Cup Coffeemaker", 174.99f));
        order.setStatus(OrderStatus.ASSIGNED_TO_DELIVERER);
        order.setDeliveryAddress(new Address(26, "Wardencourt Dr.", "",
                "Scarborough", "ON", "M1T 2H3", 43.783300f, -79.307182f));

        orders.put(order.getId(), order);

        order = new Order();
        order.setId(4);
        order.setNumber(345875);
        order.setClient(clients.get(4));
        order.addProduct(new Product("Asus Transformer Book T101HA-C4-GR 10.1-Inch 2 in 1 Touchscreen Laptop", 383.21f));
        order.setStatus(OrderStatus.ASSIGNED_TO_DELIVERER);
        order.setDeliveryAddress(new Address(38, "Eaglestone Rd", "",
                "Scarborough", "ON", "M1T 2J6", 43.783954f, -79.304524f));

        orders.put(order.getId(), order);
    }

    private static void populateDeliverers() {
        Deliverer deliverer = new Deliverer();

        deliverer.setId(1);
        deliverer.setName("John Doe");
        deliverer.addOrder(orders.get(3));
        deliverer.setCurrentLat(43.790505f);
        deliverer.setCurrentLng(-79.302339f);

        deliverers.put(deliverer.getId(), deliverer);

        deliverer = new Deliverer();
        deliverer.setId(2);
        deliverer.setName("Jane Smith");
        deliverer.addOrder(orders.get(4));
        deliverer.setCurrentLat(43.776807f);
        deliverer.setCurrentLng(-79.277744f);

        deliverers.put(deliverer.getId(), deliverer);
    }

    public static Enumeration<Client> getClients() {
        return clients.elements();
    }

    public static void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    public static Enumeration<Order> getOrders() {
        return orders.elements();
    }

    public static void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public static Enumeration<Deliverer> getDeliverers() {
        return deliverers.elements();
    }

    public static Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public static void UpdateDeliverer(int delivererId, Order order) {

        Enumeration<Deliverer> delivs = deliverers.elements();
        while (delivs.hasMoreElements()) {
            Deliverer item = delivs.nextElement();

            if (item.getOrdersAssigned().contains(order)) {
                if (item.getId() == delivererId) {
                    return;
                } else {
                    item.getOrdersAssigned().remove(order);
                }
            }
        }

        Deliverer item = deliverers.get(delivererId);
        item.addOrder(order);
    }
}
