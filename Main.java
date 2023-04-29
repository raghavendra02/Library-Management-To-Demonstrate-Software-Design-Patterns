import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import controller.BookController;
import controller.UserController;
import view.BookView;
import observer.NotifyData;
import view.BorrowingView;
import view.UserView;

public class Main {
    public static void main(String[] args) throws ParseException, SQLException {
        UserController userController = new UserController();
        UserView userView = new UserView(userController);
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);

        BorrowingView borrowingView = new BorrowingView();

        NotifyData notifyData = new NotifyData();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage users");
            System.out.println("3. Manage borrowings");
            System.out.println("4. Show Notifications");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookView.showBookMenu();
                    break;
                case 2:
                    userView.displayUserMenu();
                    break;
                case 3:
                    borrowingView.displayBorrowings();
                    break;
                case 4:
                    notifyData.displayNotif();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
    }
}
