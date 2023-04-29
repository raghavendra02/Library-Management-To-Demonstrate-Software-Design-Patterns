package controller;

import java.util.List;

import dao.UserDAO;
import model.User;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    public void addUser(User user) {
        userDAO.insertUser(user);
    }

    public User getUser(int id) {
        return userDAO.selectUser(id);
    }

    public List<User> getAllUsers() {
        return userDAO.selectAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
