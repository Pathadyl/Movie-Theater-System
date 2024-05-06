package model;

public abstract class User {
    private String userName;
    private String password;
    private Role role;
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
