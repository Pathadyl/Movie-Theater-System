package model;

public class Guest extends Customer{
    public Guest(int id, String userName, String password, String name, String phone, String email) {
        super(id, userName, password, name, phone, email);
        setRole(Role.CUSTOMER);
    }
}

