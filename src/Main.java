import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseConnection databaseConnection = new PostgreSqlConnection();

    public static void main(String[] args) {
        boolean running = true;
        boolean isLoggedIn = false;

        while (running) {
            if (isLoggedIn) {
                System.out.println("1. Show all movies");
                System.out.println("2. Book ticket");
                System.out.println("3. Logout");
            } else {
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Quit");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (isLoggedIn) {
                        ShowAllMovies showAllMovies = new ShowAllMovies(databaseConnection);
                        showAllMovies.showAllMovies();
                    } else {
                        Login login = new Login(databaseConnection);
                        isLoggedIn = login.login();
                    }
                    break;
                case 2:
                    if (isLoggedIn) {
                        System.out.print("Enter movie ID: ");
                        int movieId = scanner.nextInt();
                        scanner.nextLine();
                        BookTicket bookTicket = new BookTicket(databaseConnection);
                        bookTicket.bookTicket(movieId);
                    } else {
                        Register register = new Register(databaseConnection);
                        register.register();
                    }
                    break;
                case 3:
                    if (isLoggedIn) {
                        isLoggedIn = false;
                        System.out.println("Logout successful!");
                    } else {
                        running = false;
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
