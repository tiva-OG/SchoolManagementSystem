import com.tiva.School.SchoolController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "210598";
        Connection connection = DriverManager.getConnection(url, username, password);
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        SchoolController schoolController = new SchoolController(connection);

        System.out.println("****************************");
        System.out.println("WELCOME TO THE SCHOOL PORTAL");
        System.out.println("****************************");
        System.out.println("Enter `a` to login as admin");
        System.out.println("Enter `u` to login as user");
        System.out.print("Please enter choice: ");
        String choice = scanner.next();

        switch (choice) {
            case "a"->schoolController.adminPortal();
            case "u"->schoolController.userPortal();
            default -> System.out.println("Invalid choice");
        }
    }

}
