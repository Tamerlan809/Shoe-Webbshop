import javax.xml.transform.Result;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.Properties;

public class Repository {

    List<Kund> loggaIn(String namn, String lösenord) throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stmt = c.prepareStatement("select id, personnummer, namn, ort, lösenord from Kund where namn = ? and lösenord = ?");

        ){
            stmt.setString(1,namn);
            stmt.setString(2,lösenord);
            ResultSet rs = stmt.executeQuery();
            List<Kund> kund = new ArrayList<>();
            while(rs.next()){
                Kund temp =new Kund(rs.getInt("id"), rs.getString("personnummer"),
                        rs.getString("namn"), rs.getString("ort"),
                        rs.getString("lösenord"));
                kund.add(temp);
            }
            return kund;
        }catch (SQLException e){
            throw new IOException(e);
        }
    }
    List<Skor> skor() throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select id, storlek, färg, pris, märke, lager from Skor");
        ){
            List<Skor> skor = new ArrayList<>();
            while (rs.next()) {
                Skor temp = new Skor(rs.getInt("id"), rs.getString("storlek"),
                        rs.getString("färg"), rs.getInt("pris"),
                        rs.getString("märke"), rs.getInt("lager"));
                skor.add(temp);
            }
            return skor;
        }catch (SQLException e){
            throw new IOException(e);
        }
    }

    List<Kund> kund() throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select id, personnummer, namn, ort, lösenord from Kund");
        ){
            List<Kund> kund = new ArrayList<>();
            while (rs.next()) {
                Kund temp = new Kund(rs.getInt("id"), rs.getString("personnummer"),
                        rs.getString("namn"), rs.getString("ort"),
                        rs.getString("lösenord"));
                kund.add(temp);
            }
            return kund;
        }catch (SQLException e){
            throw new IOException(e);
        }
    }

    List<Beställning> beställning() throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select Beställning.id, Beställning.datum, Beställning.totalpris, Beställning.kundId, Kund.personnummer, Kund.namn, Kund.ort, Kund.lösenord from Beställning join Kund on Beställning.kundId = Kund.id");
        ){
            List<Beställning> beställning = new ArrayList<>();
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

    List<Order> order() throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select `Order`.id, `Order`.beställningId, `Order`.skorId, Skor.storlek, Skor.färg, Skor.pris, Skor.märke, Skor.lager from `Order` join Skor on `Order`.skorId = Skor.id");
        ){
            List<Order> order = new ArrayList<>();
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