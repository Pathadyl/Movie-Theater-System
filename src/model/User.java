package model;

public abstract class User {
    private int id;
    private String userName;
    private String password;
    private Role role;
    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
