package view;

import controller.BookController;
import factory.BookFactory;
import model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class BookView {
    private BookController bookController;
    private Scanner scanner;

    public BookView(BookController bookController) {
        this.bookController = bookController;
        scanner = new Scanner(System.in);
    }

    public void showBookMenu() throws SQLException {
        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Update Book");
            System.out.println("5. Search Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    searchBooks();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addBook() throws SQLException {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book year: ");
        String  year = scanner.nextLine();

        System.out.print("Enter book type: ");
        String type = scanner.nextLine();

        if(bookController.addBook(null, title, author, year, true, type)) {
            System.out.println("Book added successfully");
        } else{

        }
    }

    private void viewAllBooks() throws SQLException {
        List<Book> books = bookController.getAllBooks();
        System.out.println("List of all books:");
        System.out.println("ID\tTitle\tAuthor\tYear\tAvailable");
        for (Book book : books) {
            System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor() + "\t" +
                    book.getYear() + "\t" + book.isAvailable());
        }
    }

    private void deleteBook() throws SQLException {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        bookController.deleteBook(id);
        System.out.println("Book deleted successfully");
    }
    public void searchBooks() throws SQLException {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        bookController.searchBooks(keyword);
    }
    public void updateBook() {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        bookController.updateBook(id);
    }


    public void displayMessage(String s) {
        System.out.println(s);
    }

    public void displayBookDetails(Book book) {
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publication year: " + book.getYear());
        System.out.println("Availability: " + book.isAvailable());
        System.out.println("Type: " + book.getType());
    }

    public String[] getBookUpdates() {
        String[] updates = new String[5];
        System.out.print("Enter new title (press enter to skip): ");
        updates[0] = scanner.nextLine();
        System.out.print("Enter new author (press enter to skip): ");
        updates[1] = scanner.nextLine();
        System.out.print("Enter new year (press enter to skip): ");
        updates[2] = scanner.nextLine();
        System.out.print("Enter new availability (true/false, press enter to skip): ");
        updates[3] = scanner.nextLine();
        System.out.print("Enter new book type (press enter to skip): ");
        updates[4] = scanner.nextLine();
        return updates;
    }


}

