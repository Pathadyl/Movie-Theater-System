package model;

public class Admin extends User{
    public Admin(String userName, String password) {
        super(userName, password);
        setRole(Role.ADMIN);
    }

}
