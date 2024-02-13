import javax.xml.transform.Result;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.Properties;

// This class manages database operations related to customers, orders, and shoes.
public class Repository {

    // Method to authenticate a user and return a list of customer details if the credentials are valid.
    List<Kund> loggaIn(String namn, String lösenord) throws IOException{
        //Loads properties file to read the database connection settings.
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        //Connecting to the database using properties files.
        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stmt = c.prepareStatement("select id, personnummer, namn, ort, lösenord from Kund where namn = ? and lösenord = ?");

        ){
            // Set the parameters for the prepared statement using the provided username and password.
            stmt.setString(1,namn);
            stmt.setString(2,lösenord);
            ResultSet rs = stmt.executeQuery();
            List<Kund> kund = new ArrayList<>();
            // Process the query results, creating and adding customer objects to the list.
            while(rs.next()){
                Kund temp =new Kund(rs.getInt("id"), rs.getString("personnummer"),
                        rs.getString("namn"), rs.getString("ort"),
                        rs.getString("lösenord"));
                kund.add(temp);
            }
            return kund; //// Return the list of customers.
        }catch (SQLException e){
            throw new IOException(e);
        }
    }

    // Method to retrieve a list of all shoes in the database.
    List<Skor> skor() throws IOException{
        //Loads properties file to read the database connection settings.
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        //Connecting to the database using properties files.
        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select id, storlek, färg, pris, märke, lager from Skor");
        ){
            List<Skor> skor = new ArrayList<>();
            // Process the query results, creating and adding shoe objects to the list.
            while (rs.next()) {
                Skor temp = new Skor(rs.getInt("id"), rs.getString("storlek"),
                        rs.getString("färg"), rs.getInt("pris"),
                        rs.getString("märke"), rs.getInt("lager"));
                skor.add(temp);
            }
            return skor; // Return the list of shoes.
        }catch (SQLException e){
            throw new IOException(e);
        }
    }

    //Retrieves a list of all customers form database.
    List<Kund> kund() throws IOException{
        //Loads properties file to read the database connection settings.
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        //Connecting to the database using properties files.
        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select id, personnummer, namn, ort, lösenord from Kund");
        ){
            List<Kund> kund = new ArrayList<>();
            // Process the query results, creating and adding customer objects to the list.
            while (rs.next()) {
                Kund temp = new Kund(rs.getInt("id"), rs.getString("personnummer"),
                        rs.getString("namn"), rs.getString("ort"),
                        rs.getString("lösenord"));
                kund.add(temp);
            }
            return kund; //return the list of customers.
        }catch (SQLException e){
            throw new IOException(e);
        }
    }

    //Method to retrieve a list of all Beställnings in a databse with details of customers.
    List<Beställning> beställning() throws IOException{
        //Loads properties file to read the database connection settings.
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        //Connecting to the database using properties files.
        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select Beställning.id, Beställning.datum, Beställning.totalpris, Beställning.kundId, Kund.personnummer, Kund.namn, Kund.ort, Kund.lösenord from Beställning join Kund on Beställning.kundId = Kund.id");
        ){
            List<Beställning> beställning = new ArrayList<>();
            // Process the query results, creating and adding Beställning objects with embedded customer details to the list.
            while (rs.next()) {
                Kund kund = new Kund(rs.getInt("kundId"),
                        rs.getString("personnummer"), rs.getString("namn"),
                        rs.getString("ort"), rs.getString("lösenord"));
                Beställning temp = new Beställning(rs.getInt("id"),
                        rs.getDate("datum"),
                        rs.getInt("totalpris"), kund);
                beställning.add(temp);
            }
            return beställning;
        }catch (SQLException e){
            throw new IOException(e);
        }
    }

    //Method to retrieve a list of all Orders in a database with details of shoes.
    List<Order> order() throws IOException{
        //Loads properties file to read the database connection settings.
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        //Connecting to the database using properties files.
        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select `Order`.id, `Order`.beställningId, `Order`.skorId, Skor.storlek, Skor.färg, Skor.pris, Skor.märke, Skor.lager from `Order` join Skor on `Order`.skorId = Skor.id");
        ){
            List<Order> order = new ArrayList<>();
            // Process the query results, creating and adding Order objects with embedded shoe details to the list.
            while (rs.next()) {
                Skor skor = new Skor(rs.getInt("skorId"), rs.getString("storlek"),
                        rs.getString("färg"), rs.getInt("pris"),
                        rs.getString("märke"), rs.getInt("lager"));
                Order temp = new Order(rs.getInt("id"), rs.getInt("beställningId"),
                        skor);
                order.add(temp);
            }
            return order;
        }catch (SQLException e){
            throw new IOException(e);
        }
    }
}