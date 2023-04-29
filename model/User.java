package model;

import observer.notifListener;

public class User implements notifListener{
    private int ID;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private int msgSub;
    
    public User(int ID, String name, String address, String phoneNumber, String email) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public User(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }
    
    public User(int ID, String name, String address, String phoneNumber, String email, int msgSub) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.msgSub = msgSub;
    }

    public User(String name, String address, String phoneNumber, String email, int msgSub) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.msgSub = msgSub;
    }    

    // Getters and Setters
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public int getMsgSub() {
        return msgSub;
    }
    
    public void setMsgSub(int msgSub) {
        this.msgSub = msgSub;
    }

    // Observer

    @Override
    public void update() {
        return;
    }
}
