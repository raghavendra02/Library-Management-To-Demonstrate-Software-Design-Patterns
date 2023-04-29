package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.BookController;
import controller.BorrowingController;
import controller.UserController;
import model.Book;
import model.Borrowing;
import model.User;

public class BorrowingView {
    private BorrowingController borrowingController;

    public BorrowingView() {
        borrowingController = new BorrowingController();
    }

    public void displayBorrowings() throws ParseException, SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== Borrowings ====");
        System.out.println("1. View All Borrowings");
        System.out.println("2. Add New Borrowing");
        System.out.println("3. Return a Borrowed Book");
        System.out.println("4. View Borrowings by User ID");
        System.out.println("5. Exit");

        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View all borrowings
                    List<Borrowing> borrowings = borrowingController.getAllBorrowings();
                    displayBorrowingList(borrowings);
                    break;
                case 2:
                    // Add new borrowing
                    Borrowing newBorrowing = readBorrowingData();
                    borrowingController.addBorrowing(newBorrowing);
                    System.out.println("New borrowing added successfully!");
                    break;
                case 3:
                    // Return a borrowed book
                    System.out.print("Enter the ID of the borrowing to return: ");
                    int borrowingID = scanner.nextInt();
                    borrowingController.deleteBorrowing(borrowingID);
                    System.out.println("Borrowed book returned successfully!");
                    break;
                case 4:
                    // Exit
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private Borrowing readBorrowingData() throws ParseException, SQLException {
        Scanner scanner = new Scanner(System.in);

        UserController userController = new UserController();
        System.out.print("Enter user ID: ");
        int userID = scanner.nextInt();
        User user = userController.getUser(userID);

        BookController bookController = new BookController();
        System.out.print("Enter book ID: ");
        int bookID = scanner.nextInt();
        Book book = bookController.getBookById(bookID);


        System.out.print("Enter borrowing date (yyyy-mm-dd): ");
        String borrowingDateString = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date borrowingDate = dateFormat.parse(borrowingDateString);
        

        System.out.print("Enter return date (yyyy-mm-dd): ");
        String returnDateString = scanner.next();
        SimpleDateFormat retDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date returnDate = retDateFormat.parse(returnDateString);

        return new Borrowing(user, book, borrowingDate, returnDate);
    }

    private void displayBorrowingList(List<Borrowing> borrowings) {
        System.out.println("==== Borrowings ====");
        System.out.printf("%-5s %-20s %-20s %-20s %-20s\n", "ID", "User", "Book", "Borrowing Date", "Return Date");
        for (Borrowing borrowing : borrowings) {
            System.out.printf("%-5d %-20s %-20s %-20s %-20s\n",
                    borrowing.getID(), borrowing.getUser().getName(), borrowing.getBook().getTitle(),
                    borrowing.getBorrowingDate(), borrowing.getReturnDate());
        }
    }
    
    private void displayBooksList(List<Book> books) {
        System.out.println("==== Borrowings ====");
        System.out.printf("%-5s %-20s\n", "ID", "Title");
        for (Book book : books) {
            System.out.printf("%-5d %-20s\n", book.getId(), book.getTitle());
        }
    }
}
