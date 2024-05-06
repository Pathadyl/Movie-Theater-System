package model;

public class Guest extends Customer{
    public Guest(int id, String name, String phone, String email) {
        super(id, name, phone, email);
        setRole(Role.CUSTOMER);
    }
}
