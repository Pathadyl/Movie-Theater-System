package model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public abstract class Customer extends User{
    private String name;
    private String phone;
    private String email;
    private Map<Movie, Integer> cart;
    private List<Bill> billList;

    public Customer(int id, String userName, String password, String name, String phone, String email) {
        super(id, userName, password);
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
    public List<Bill> getBillList() {
        return billList;
    }
}
