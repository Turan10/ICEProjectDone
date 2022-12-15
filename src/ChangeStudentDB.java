import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ChangeStudentDB extends DBStudents {
    public static void main(String[] args) throws SQLException {
        // Replace with your own database URL, username, and password
        String dbUrl = "jdbc:mysql://localhost:3306/ICE";
        String username = "root";
        String password = "martin123";

        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);

            Scanner scanner = new Scanner(System.in);

            // Read the user's input
            System.out.println("Enter the user's ID you want to change:");
            int id = scanner.nextInt();

            scanner.nextLine();
            System.out.println("Update current email address:");
            String email = scanner.nextLine();

            System.out.println("Update current phone number:");
            String phoneNumber = scanner.nextLine();


            // Create a SQL UPDATE query
            String sql = "UPDATE StudentInformation SET email = ?, phone_number = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the values of the query parameters
            statement.setString(1, email);
            statement.setString(2, phoneNumber);
            statement.setInt(3, id);

            // Execute the query and get the number of rows affected
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("The database was updated successfully!");
            } else {
                System.out.println("An error occurred while updating the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database.");
            e.printStackTrace();
        }
    }
}


