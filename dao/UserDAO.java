package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {

    // SQL queries
    private static final String INSERT_USER_SQL = "INSERT INTO users (name, address, phone_number, email, msgSub) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, address = ?, phone_number = ?, email = ? WHERE id = ?";
    private static final String FETCH_SUBS = "Select * from users where msgSub = 1";

    // Connect to MySQL database
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Insert user into database
    public void insertUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getMsgSub());
            preparedStatement.executeUpdate();
            System.out.println("DB inserting user");
        } catch (SQLException e) {
            System.out.println("Error inserting user");
            e.printStackTrace();
        }
    }

    // Select user by ID
    public User selectUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                user = new User(id, name, address, phoneNumber, email);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting user");
            e.printStackTrace();
        }
        return user;
    }

    // Select all users from database
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                users.add(new User(id, name, address, phoneNumber, email));
            }
        } catch (SQLException e) {
            System.out.println("Error selecting all users");
            e.printStackTrace();
        }
        return users;
    }


    // Select all Subscribers from database
    public List<User> FetchSubs() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FETCH_SUBS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                users.add(new User(id, name, address, phoneNumber, email));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users");
            e.printStackTrace();
        }
        return users;
    }


    // Delete user from database
    public boolean deleteUser(User user) {
        boolean rowDeleted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setInt(1, user.getID());
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user");
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Update user in database
    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating user");
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection");
            e.printStackTrace();
        }
    }
}
