package model;

public class Guest extends Customer{
    public Guest(String userName, String password, String name, String phone, String email) {
        super(userName, password, name, phone, email);
        setRole(Role.CUSTOMER);
    }
}

