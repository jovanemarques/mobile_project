package ca.centennialcollege.comp304_miniproject.models;

public class DataRepository {
    public static void SeedData() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Eduardo");
        user1.setRole(1);
        user1.setAddress("Road Street 999, Toronto, ON");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jovane");
        user2.setRole(2);
        user1.setAddress("Road Street 888, Toronto, ON");

        User user3 = new User();
        user3.setId(3);
        user3.setName("Aesha");
        user3.setRole(1);
        user1.setAddress("Road Street 777, Toronto, ON");

        User user4 = new User();
        user4.setId(4);
        user4.setName("Ailton");
        user4.setRole(2);
        user1.setAddress("Road Street 666, Toronto, ON");

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Smartphone");
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Laptop");

        Order order1 = new Order();
        order1.setId(1);
        order1.setNumber(123456);
        order1.setUser(user3);
        order1.addProduct(product1);
        order1.addProduct(product2);
    }
}
