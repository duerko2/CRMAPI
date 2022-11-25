package CRMarcusAPI.model;

import java.util.ArrayList;
import java.util.List;

public class BrandAreaCustomer {
    Customer customer;
    List<Order> orders = new ArrayList<>();

    public BrandAreaCustomer(Customer customer) {
        this.customer = customer;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
