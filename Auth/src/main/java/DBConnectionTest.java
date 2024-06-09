
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/jobos?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "";

        try {
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            System.out.println("Connection successful!");
            connection.close(); // Fermer la connexion après utilisation
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
