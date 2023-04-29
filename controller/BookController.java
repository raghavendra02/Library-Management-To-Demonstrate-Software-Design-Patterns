package controller;

import dao.BookDAO;
import dao.BookDAOImpl;
import factory.BookFactory;
import model.Book;
import view.BookView;

import java.sql.SQLException;
import java.util.*;


public class BookController {
    private BookDAO bookDAO;
    private Scanner scanner;
    private BookView bookview;

    public BookController() throws SQLException {
        this.bookDAO = new BookDAOImpl();
        this.scanner = new Scanner(System.in);
        this.bookview = new BookView(this);
    }

    public boolean addBook(Integer id, String title, String author, String year, boolean isAvailable, String type) throws SQLException {
        Book book = BookFactory.createBook(id, title, author, year, isAvailable, type);
        if(book!=null) {
            this.bookDAO.addBook(book);
            return true;
        } else {
            return false;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public void deleteBook(int id) throws SQLException {
        bookDAO.deleteBook(id);
    }

    public void searchBooks(String keyword) throws SQLException {
        try {
            List<Book> books = bookDAO.searchBooks(keyword);
            if (books.isEmpty()) {
                bookview.displayMessage("No books found!");
            } else {
                bookview.displayMessage("Search Results:");
                bookview.displayMessage("ID\tTitle\tAuthor\tYear\tAvailable");
                for (Book book : books) {
                    bookview.displayMessage(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor() + "\t" +
                            book.getYear() + "\t" + book.isAvailable());
                }
            }
        } catch (SQLException e) {
            bookview.displayMessage("An error occurred while searching books: " + e.getMessage());
        }
    }

    public void updateBook(int id) {
        try {
            Book book = bookDAO.getBookById(id);
            if (book == null) {
                bookview.displayMessage("Book not found!");
                return;
            }
            bookview.displayMessage("Current book details:");
            bookview.displayBookDetails(book);
            String[] newValues = bookview.getBookUpdates();
            String title = newValues[0].isEmpty() ? book.getTitle() : newValues[0];
            String author = newValues[1].isEmpty() ? book.getAuthor() : newValues[1];
            String year = newValues[2].isEmpty() ? book.getYear() : newValues[2];
            boolean isAvailable = newValues[3].isEmpty() ? book.isAvailable() : Boolean.parseBoolean(newValues[3]);
            String type = newValues[4].isEmpty() ? book.getType() : newValues[4];
            Book newBook = BookFactory.createBook(id, title, author, year, isAvailable, type);
            bookDAO.updateBook(newBook);
            bookview.displayMessage("Book updated successfully!");
        } catch (SQLException e) {
            bookview.displayMessage("An error occurred while updating book: " + e.getMessage());
        }
    }

    public Book getBookById(int bookID) throws SQLException {
        return bookDAO.getBookById(bookID);
    }

}