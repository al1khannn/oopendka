import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTicket {
    private final DatabaseConnection databaseConnection;

    public BookTicket(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void bookTicket(int movieId) {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM movies WHERE id = ?"
            );
            statement.setInt(1, movieId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int availableSeats = resultSet.getInt("available_seats");
                if (availableSeats > 0) {
                    PreparedStatement updateStatement = connection.prepareStatement(
                            "UPDATE movies SET available_seats = ? WHERE id = ?"
                    );
                    updateStatement.setInt(1, availableSeats - 1);
                    updateStatement.setInt(2, movieId);

                    int rowsUpdated = updateStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Ticket booked successfully!");
                    } else {
                        System.out.println("Ticket booking failed.");
                    }
                } else {
                    System.out.println("No available seats for this movie.");
                }
            } else {
                System.out.println("Movie not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}