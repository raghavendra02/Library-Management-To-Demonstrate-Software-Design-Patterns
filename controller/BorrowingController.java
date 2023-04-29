package controller;

import java.util.ArrayList;
import java.util.List;

import dao.BorrowingDAO;
import model.Book;
import model.Borrowing;

public class BorrowingController {
    private BorrowingDAO borrowingDAO = new BorrowingDAO();

    public void addBorrowing(Borrowing borrowing) {
        borrowingDAO.insertBorrowing(borrowing);
    }

    public Borrowing getBorrowing(int id) {
        return borrowingDAO.selectBorrowing(id);
    }

    public List<Borrowing> getAllBorrowings() {
        return borrowingDAO.selectAllBorrowings();
    }

    public void updateBorrowing(Borrowing borrowing) {
        borrowingDAO.updateBorrowing(borrowing);
    }

    public void deleteBorrowing(int id) {
        borrowingDAO.deleteBorrowing(id);
    }

    public List<Book> getBorrowingsByUserID(int userID) {
        List<Borrowing> borrowings = (List<Borrowing>) borrowingDAO.selectBorrowing(userID);
        List<Book> books = new ArrayList<>();
        if (borrowings != null) {
            for (Borrowing borrowing : borrowings) {
                books.add(borrowing.getBook());
            }
        }
        return books;
    }
    
}
