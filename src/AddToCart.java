import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AddToCart {
    void AddToCart(int kund_id, int beställning_id, int skor_id) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
             CallableStatement stmt = c.prepareCall("call AddToCart(?, ? ,?, ?)");

        ) {
            stmt.setInt(1, kund_id);
            stmt.setInt(2, beställning_id);
            stmt.setInt(3, skor_id);
            stmt.registerOutParameter(4,Types.VARCHAR);
            stmt.executeQuery();
            System.out.println(stmt.getString(4));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
