import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ShowAllMovies {
    private final DatabaseConnection databaseConnection;

    public ShowAllMovies(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void showAllMovies() {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM movies"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                int duration = resultSet.getInt("duration");
                int availableSeats = resultSet.getInt("available_seats");

                System.out.printf("ID: %d | Title: %s | Genre: %s | Duration: %d min | Available Seats: %d\n",
                        id, title, genre, duration, availableSeats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
