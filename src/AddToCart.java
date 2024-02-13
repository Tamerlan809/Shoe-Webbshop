import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Class to add a shoe order to the cart.
public class AddToCart {
    void AddToCart(int kund_id, int beställning_id, int skor_id) throws IOException {

        //Loads properties file to read the database connection settings.
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        //Connecting to database with callable statement
        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             CallableStatement stmt = c.prepareCall("call AddToCart(?, ? ,?, ?)");

        ) {
            //Setting parameters for the callable statemnt.
            stmt.setInt(1, kund_id);
            stmt.setInt(2, beställning_id);
            stmt.setInt(3, skor_id);
            //Output parameter. Used for a message that returns from the database.
            stmt.registerOutParameter(4,Types.VARCHAR);
            //Executing query that calls stored procedure from database with given parameters
            stmt.executeQuery();
            //Prints message returned from database.
            System.out.println(stmt.getString(4));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
