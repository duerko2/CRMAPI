package CRMarcusAPI.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Customer {
    int accountNo;
    String accountName;
    String country;
    int salesRepId;

    public Customer(int accountNo,
            String accountName,
            String country,
            int salesRepId){
        this.accountName=accountName;
        this.accountNo=accountNo;
        this.salesRepId=salesRepId;
        this.country=country;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public int getSalesRepId() {
        return salesRepId;
    }

    public String getCountry() {
        return country;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Customer)) {
            return false;
        }

        // typecast o to Customer so that we can compare data members
        Customer c = (Customer) obj;

        // Compare the data members and return accordingly
        return c.getAccountNo()==accountNo;
    }
}
