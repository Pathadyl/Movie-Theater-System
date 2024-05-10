package service;

import dao.GuestDao;
import model.Guest;
import model.User;

public class GuestService extends CustomerService {
    private GuestDao guestDao;

    public GuestService(Guest guest) {
        super(guest);
        guestDao = new GuestDao();
    }
    @Override
    public User logIn(String userName, String password) {
        return guestDao.createGuest();
    }
}
