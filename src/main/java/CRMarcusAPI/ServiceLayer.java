package CRMarcusAPI;

import CRMarcusAPI.model.BrandAreaCustomer;
import CRMarcusAPI.model.Customer;
import CRMarcusAPI.model.Order;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class ServiceLayer {
    DataLayer dataLayer = new DataLayer();
    public void postCustomer(Customer c) throws SQLException {
        dataLayer.postCustomer(c.getAccountName(),c.getCountry(),c.getSalesRepId());
    }

    public Customer getCustomer(String id) {
        return null;
    }

    public List<Customer> getCustomers() throws SQLException{
        List<Customer> customerList = new ArrayList<>();

        ResultSet resultSet = dataLayer.getCustomers();

        resultSet.beforeFirst(); // Set pointer for resultSet.next()

        // Cycles through the rows of customers and adds them to the lsit
        // -----------------------
        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4));
            customerList.add(customer);
        }

        return customerList;
    }

    public List<Order> getOrders() throws SQLException{
        List<Order> orderList = new ArrayList<>();

        ResultSet resultSet = dataLayer.getOrders();

        resultSet.beforeFirst(); // Set pointer for resultSet.next()

        // Cycles through the rows of orders and adds them to the lsit
        // -----------------------
        while (resultSet.next()) {
            ResultSet customerResultSet = dataLayer.getCustomer(resultSet.getInt(10));
            customerResultSet.beforeFirst();
            customerResultSet.next();
            Customer customer = new Customer(customerResultSet.getInt(1),
                    customerResultSet.getString(2),
                    customerResultSet.getString(3),
                    customerResultSet.getInt(4));

            Order order = new Order(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    new Date(resultSet.getDate(6).getTime()),
                    resultSet.getInt(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    customer
            );
            orderList.add(order);
        }

        return orderList;
    }

    public List<String> getUniqueBrands() throws SQLException{
        List<String> names = new ArrayList<>();
        ResultSet resultSet = dataLayer.getBrands();
        resultSet.beforeFirst(); // Set pointer for resultSet.next()

        // Cycles through the rows of orders and adds them to the lsit
        // -----------------------
        while (resultSet.next()) {
            names.add(resultSet.getString(1));
        }
        return names;
    }

    public List<String> getAreaNamesByBrand(String brand) throws SQLException{
        List<String> names = new ArrayList<>();
        ResultSet resultSet = dataLayer.getAreas(brand);
        resultSet.beforeFirst(); // Set pointer for resultSet.next()

        // Cycles through the rows of orders and adds them to the lsit
        // -----------------------
        while (resultSet.next()) {
            names.add(resultSet.getString(1));
        }
        return names;

    }

    public List<BrandAreaCustomer> getBrandAreaCustomer(String area,String brand) throws SQLException{
        List<Customer> customers = new ArrayList<>();
        List<BrandAreaCustomer> brandAreaCustomers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();


        ResultSet resultSet = dataLayer.getBrandAreaCustomer(brand,area);
        resultSet.beforeFirst(); // Set pointer for resultSet.next()

        // Adds all customers and order to a list
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getInt(9),
                    resultSet.getString(1),
                    resultSet.getString(10),
                    resultSet.getInt(8)
            );
            customers.add(customer);
            orders.add(
                    new Order(
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            new Date(resultSet.getDate(7).getTime()),
                            resultSet.getInt(8),
                            brand,
                            area,
                            customer
                    )
            );
        }




        // Remove duplicate customers.
        List<Customer> customersWithNoDuplicates = new ArrayList<>();
        for (Customer customer : customers) {
            if (!customersWithNoDuplicates.contains(customer)) {
                customersWithNoDuplicates.add(customer);
            }
        }

        // add all customers to list of BrandAreaCustomer. Then add all orders corresponding with the customer to the BrandAreaCustomer
        for(Customer customer : customersWithNoDuplicates){
            BrandAreaCustomer newBrandAreaCustomer = new BrandAreaCustomer(customer);
            for(Order order : orders){
                if(order.getCustomer().getAccountNo()==customer.getAccountNo()){
                    newBrandAreaCustomer.getOrders().add(order);
                }
            }
            brandAreaCustomers.add(newBrandAreaCustomer);
        }


        return brandAreaCustomers;
    }
}
