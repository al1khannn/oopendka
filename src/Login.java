import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private final DatabaseConnection databaseConnection;

    public Login(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?"
            );
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}