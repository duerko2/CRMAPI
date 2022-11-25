package CRMarcusAPI.model;

import java.util.Date;

public class Order {
    private int orderNum;
    private int amount;
    private String currencyName;
    private String orderType;
    private String seasonId ;
    private Date orderDate;
    private int salesRepId;
    private String brandName;
    private String areaName;
    private Customer customer;


    public Order(int orderNum, int amount, String currencyName, String orderType, String seasonId, Date orderDate, int salesRepId, String brandName, String areaName,Customer customer) {
        this.orderNum = orderNum;
        this.amount = amount;
        this.currencyName = currencyName;
        this.orderType = orderType;
        this.seasonId = seasonId;
        this.orderDate = orderDate;
        this.salesRepId = salesRepId;
        this.brandName = brandName;
        this.areaName = areaName;
        this.customer=customer;
    }
    public Order(int orderNum, int amount, String currencyName, String orderType, String seasonId, Date orderDate, int salesRepId, String brandName, String areaName) {
        this.orderNum = orderNum;
        this.amount = amount;
        this.currencyName = currencyName;
        this.orderType = orderType;
        this.seasonId = seasonId;
        this.orderDate = orderDate;
        this.salesRepId = salesRepId;
        this.brandName = brandName;
        this.areaName = areaName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
