package CRMarcusAPI;

import CRMarcusAPI.model.BrandAreaCustomer;
import CRMarcusAPI.model.Customer;
import CRMarcusAPI.model.Order;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerLayer {

    private ServiceLayer serviceLayer = new ServiceLayer();


    /**
     * Client requests to add customer
     * @param c
     * @return
     */
    @PostMapping(value="/customers")
    public String postCustomer(@RequestBody Customer c){
        try{
            serviceLayer.postCustomer(c);
        } catch(
            SQLTimeoutException timeoutException){
            return "TimeoutException";
        } catch (
            SQLException sqlException) {
            return "SQLException";
        }
        return "COMPLETE";
    }

    @GetMapping(value = "/customers")
    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();
        try {
            customers = serviceLayer.getCustomers();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customers;
    }

    @GetMapping(value = "/customers/{id}")
    public Customer getCustomer(@PathVariable("id") String id){
        Customer customer = serviceLayer.getCustomer(id);
        return customer;
    }

    @GetMapping(value = "/orders")
    public List<Order> getOrders(){
        List<Order> orders = new ArrayList<>();
        try {
            orders = serviceLayer.getOrders();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return orders;
    }


    @GetMapping(value = "/brands/unique")
    public List<String> getUniqueBrands(){
        List<String> names = new ArrayList<>();
        try {
            names = serviceLayer.getUniqueBrands();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return names;
    }
    @GetMapping(value = "/brands/{brand}/areaNames")
    public List<String> getAreaNamesByBrand(@PathVariable("brand") String brand){
        List<String> names = new ArrayList<>();
        try {
            names = serviceLayer.getAreaNamesByBrand(brand);
        } catch (SQLException sqlException) {
            names.add("ERROR");
            return names;
        }
        return names;
    }

    @GetMapping(value = "/brands/{brand}/{areaName}/customers")
    public List<BrandAreaCustomer> getBrandAreaCustomer(@PathVariable("brand") String brand,@PathVariable("areaName") String areaNames){
        List<BrandAreaCustomer> result = new ArrayList<>();
        try {
            result = serviceLayer.getBrandAreaCustomer(areaNames,brand);
        } catch (SQLException sqlException) {
            return result;
        }
        return result;
    }

}


