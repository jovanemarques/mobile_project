package ca.centennialcollege.comp304_miniproject.models;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static DataRepository dataRepository;
    private static List<User> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    public static void SeedData() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Eduardo");
        user1.setRole(1);
        user1.setAddress("Road Street 999, Toronto, ON");

        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jovane");
        user2.setRole(2);
        user1.setAddress("Road Street 888, Toronto, ON");

        users.add(user2);

        User user3 = new User();
        user3.setId(3);
        user3.setName("Aesha");
        user3.setRole(1);
        user1.setAddress("Road Street 777, Toronto, ON");

        users.add(user3);

        User user4 = new User();
        user4.setId(4);
        user4.setName("Ailton");
        user4.setRole(2);
        user1.setAddress("Road Street 666, Toronto, ON");

        users.add(user4);

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Smartphone");

        products.add(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Laptop");

        products.add(product2);

        Order order1 = new Order();
        order1.setId(1);
        order1.setNumber(123456);
        order1.setUser(user3);
        order1.addProduct(product1);
        order1.addProduct(product2);

        orders.add(order1);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }
}
