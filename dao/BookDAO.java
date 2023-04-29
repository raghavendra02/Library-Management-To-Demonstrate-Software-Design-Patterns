package dao;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks() throws SQLException;
    void addBook(Book book) throws SQLException;
    void deleteBook(int id) throws SQLException;

    void updateBook(Book book) throws SQLException;
    List<Book> searchBooks(String keyword) throws SQLException;

    Book getBookById(int id) throws SQLException;
}
