package CRMarcusAPI;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DataLayer {
    // Tilpas variable til jeres database.
    // -----------------------------------
    String host = "localhost";          //host is "localhost" or "127.0.0.1"
    String port = "3306";               //port is where to communicate with the RDBM system
    String database = "crm";        //database containing tables to be queried
    String cp = "utf8";                 //Database codepage supporting danish (i.e. æøåÆØÅ)

    // Set username og password.
    // -------------------------
    String username = "root";		// Username for connection
    String password = "dererentyr2";	// Password for username

    String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=" + cp;

    public DataLayer(){
    }

    public void postCustomer(String accountName,
                             String country,
                             int salesRepId) throws SQLException {
            // Get a connection.
            // -----------------
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create and execute Update.
            // --------------------------
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer VALUES (DEFAULT, ?, ?, ?)");
            statement.setString(1, accountName);
            statement.setString(2,country);
            statement.setInt(3,salesRepId);
            statement.execute();

            // Close connection.
            // -----------------
            connection.close();
    }

    public ResultSet getCustomers() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM customer");
    }

    public ResultSet getOrders() throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM orders");
    }

    public ResultSet getCustomer(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE accountNo=?;");
        statement.setInt(1,id);

        return statement.executeQuery();
    }

    public ResultSet getBrands() throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT DISTINCT brandName FROM brand;");
    }

    public ResultSet getAreas(String id) throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("AREA");

        PreparedStatement statement = connection.prepareStatement("SELECT areaName FROM brand WHERE brandName=?;");
        statement.setString(1,id);

        return statement.executeQuery();
    }
}
