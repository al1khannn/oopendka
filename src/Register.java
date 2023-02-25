import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Register {
    private final DatabaseConnection databaseConnection;

    public Register(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (username, password, email) VALUES (?, ?, ?)"
            );
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}