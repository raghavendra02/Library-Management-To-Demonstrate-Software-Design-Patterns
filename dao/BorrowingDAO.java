package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Borrowing;
import model.User;

public class BorrowingDAO {
    // SQL queries
    private static final String INSERT_BORROWING_SQL = "INSERT INTO borrowing (user_id, book_id, borrowing_date, return_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BORROWING_BY_ID = "SELECT * FROM borrowing WHERE id = ?";
    private static final String SELECT_ALL_BORROWINGS = "SELECT * FROM borrowing";
    private static final String DELETE_BORROWING_SQL = "DELETE FROM borrowing WHERE id = ?";
    private static final String UPDATE_BORROWING_SQL = "UPDATE borrowing SET user_id = ?, book_id = ?, borrowing_date = ?, return_date = ? WHERE id = ?";

    // Connect to MySQL database
    private Connection connection;

    public BorrowingDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Insert borrowing record into database
    public void insertBorrowing(Borrowing borrowing) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROWING_SQL);
            preparedStatement.setInt(1, borrowing.getUser().getID());
            preparedStatement.setInt(2, borrowing.getBook().getId());
            preparedStatement.setDate(3, new Date(borrowing.getBorrowingDate().getTime()));
            preparedStatement.setDate(4, new Date(borrowing.getReturnDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting borrowing record");
            e.printStackTrace();
        }
    }

    // Select borrowing record by ID
    public Borrowing selectBorrowing(int id) {
        Borrowing borrowing = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWING_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = selectUser(resultSet.getInt("user_id"));
                Book book = selectBook(resultSet.getInt("book_id"));
                Date borrowingDate = resultSet.getDate("borrowing_date");
                Date returnDate = resultSet.getDate("return_date");
                borrowing = new Borrowing(id, user, book, borrowingDate, returnDate);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting borrowing record");
            e.printStackTrace();
        }
        return borrowing;
    }

    // Select all borrowing records
    public List<Borrowing> selectAllBorrowings() {
        List<Borrowing> borrowings = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BORROWINGS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                User user = selectUser(resultSet.getInt("user_id"));
                Book book = selectBook(resultSet.getInt("book_id"));
                Date borrowingDate = resultSet.getDate("borrowing_date");
                Date returnDate = resultSet.getDate("return_date");
                borrowings.add(new Borrowing(id, user, book, borrowingDate, returnDate));
            }
        } catch (SQLException e) {
            System.out.println("Error selecting all borrowing records");
            e.printStackTrace();
        }
        return borrowings;
    }

    // Delete borrowing record by ID
    public boolean deleteBorrowing(int id) {
        boolean rowDeleted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BORROWING_SQL);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting borrowing record");
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Update borrowing record
    public boolean updateBorrowing(Borrowing borrowing) {
        boolean rowUpdated = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BORROWING_SQL);
            preparedStatement.setInt(1, borrowing.getUser().getID());
            preparedStatement.setInt(2, borrowing.getBook().getId());
            preparedStatement.setDate(3, new Date(borrowing.getBorrowingDate().getTime()));
            preparedStatement.setDate(4, new Date(borrowing.getReturnDate().getTime()));
            preparedStatement.setInt(5, borrowing.getID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating borrowing record");
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // Select user record by ID
    private User selectUser(int id) {
        UserDAO userDAO = new UserDAO();
        return userDAO.selectUser(id);
    }

    // Select book record by ID
    private Book selectBook(int id) throws SQLException {
        BookDAO bookDAO = new BookDAOImpl();
        return bookDAO.getBookById(id);
    }
}