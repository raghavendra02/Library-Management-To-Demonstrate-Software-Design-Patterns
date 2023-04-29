package dao;

import factory.BookFactory;
import model.Book;
import model.FictionBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Connection connection;

    public BookDAOImpl() throws SQLException {
        super();
        this.connection = DatabaseConnection.getConnection();
    }

    public Book parseBook(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String year = resultSet.getString("year");
        boolean isAvailable = resultSet.getBoolean("is_available");
        String type = resultSet.getString("type");
        return BookFactory.createBook(id, title, author, year, isAvailable, type);
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            books.add(parseBook(resultSet));
        }
        return books;
    }
    @Override
    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, author, year, is_available, type) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getYear());
        statement.setBoolean(4, book.isAvailable());
        statement.setString(5, book.getType());
        statement.executeUpdate();
    }


    @Override
    public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM books WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        String query = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseBook(resultSet);
            } else {
                return null;
            }
        }
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title = ?, author = ?, year = ?, is_available = ?, type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getYear());
            statement.setBoolean(4, book.isAvailable());
            statement.setString(5, book.getType());
            statement.setInt(6, book.getId());
            statement.executeUpdate();
        }
    }



    @Override
    public List<Book> searchBooks(String keyword) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR year LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + keyword + "%");
        statement.setString(2, "%" + keyword + "%");
        statement.setString(3, "%" + keyword + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String year = resultSet.getString("year");
            boolean isAvailable = resultSet.getBoolean("is_available");
            String type = resultSet.getString("type");
            Book book = BookFactory.createBook(id, title, author, year, isAvailable, type);
            books.add(book);
        }
        return books;
    }
}
