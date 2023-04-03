import java.net.MalformedURLException;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver failed to load.");
            System.exit(1);
        }
         try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp11?user=root&password=")) {
            System.out.println("Connected to DB.");
            Statement stmt = conn.createStatement();
        }
           
         
            catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Failed to connect to DB.");
        }
    }
}

