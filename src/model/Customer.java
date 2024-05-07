package model;

import java.sql.Date;

public abstract class Customer extends User{
    private String name;
    private String phone;
    private String email;

    public Customer(String userName, String password, String name, String phone, String email) {
        super(userName, password);
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
