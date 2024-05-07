package service;

import dao.AdminDao;
import model.Admin;
import model.User;

import java.util.List;

public class AdminService extends UserService {
    AdminDao adminDao = new AdminDao();

    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    @Override
    public User logIn(String userName, String password) {
        return null;
    }
}
