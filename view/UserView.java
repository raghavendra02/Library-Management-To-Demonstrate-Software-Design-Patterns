package view;

import java.util.Scanner;

import controller.UserController;
import model.User;
import observer.NotifyData;

public class UserView {
    private UserController userController;
    private Scanner scanner;

    public UserView(UserController userController) {
        this.userController = userController;
        scanner = new Scanner(System.in);
    }

    public void displayUserMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add new user");
        System.out.println("2. View all users");
        System.out.println("3. Search user by ID");
        System.out.println("4. Update user");
        System.out.println("5. Delete user");
        System.out.println("6. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline character

        switch (choice) {
            case 1:
                addUser();
                break;
            case 2:
                viewAllUsers();
                break;
            case 3:
                searchUserById();
                break;
            case 4:
                updateUser();
                break;
            case 5:
                deleteUser();
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        displayUserMenu(); // continue displaying menu until user exits
    }

    private void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user address: ");
        String address = scanner.nextLine();
        System.out.print("Enter user phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        System.out.print("Enter if Notification required (1/0): ");
        Integer msgSub = scanner.nextInt();
        User user = new User(name, address, phoneNumber, email, msgSub);
        userController.addUser(user);
        System.out.println("User added successfully.");
    }

    private void viewAllUsers() {
        System.out.printf("+--------+------------------+----------------------+------------------------+\n");
        System.out.printf("| %6s | %16s | %20s | %22s |\n","ID","Name","Email","Phone Number");
        System.out.printf("+--------+------------------+----------------------+------------------------+\n");
        for (User user : userController.getAllUsers()) {
            System.out.printf("| %6d | %16s | %20s | %22s |\n",
                    user.getID(), user.getName(), user.getEmail(), user.getPhoneNumber());
        }
        System.out.printf("+--------+------------------+----------------------+------------------------+\n");
    }

    private void searchUserById() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline character

        User user = userController.getUser(id);
        if (user != null) {
            System.out.printf(
                    "\n\nID: %d, Name: %s, Email: %s, Phone Number: %s\n-----------------------------------------------\n",
                    user.getID(), user.getName(), user.getEmail(), user.getPhoneNumber());
        } else {
            System.out.println("User not found.");
        }
    }

    private void updateUser() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline character

        User user = userController.getUser(id);
        if (user != null) {
            System.out.print("Enter new user name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new user address: ");
            String address = scanner.nextLine();
            System.out.print("Enter new user phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter new user email: ");
            String email = scanner.nextLine();

            user.setName(name);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);

            userController.updateUser(user);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline character

        User user = userController.getUser(id);
        if (user != null) {
            userController.deleteUser(user);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
}
