package model;
import java.util.Date;

public class Borrowing {
    private int ID;
    private User user;
    private Book book;
    private Date borrowingDate;
    private Date returnDate;
    
    public Borrowing(int ID, User user, Book book, Date borrowingDate, Date returnDate) {
        this.ID = ID;
        this.user = user;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public Borrowing(User user, Book book, Date borrowingDate, Date returnDate) {
        this.user = user;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    // Getter and setter methods
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public Date getBorrowingDate() {
        return borrowingDate;
    }
    
    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }
    
    public Date getReturnDate() {
        return returnDate;
    }
}