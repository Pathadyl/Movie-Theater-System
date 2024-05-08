package service;

import dao.AdminDao;
import dao.MovieDao;
import model.Admin;
import model.Movie;
import model.User;

import java.util.List;

public class AdminService extends UserService {
    private AdminDao adminDao;
    private MovieDao movieDao;

    public AdminService() {
        adminDao = new AdminDao();
        movieDao = new MovieDao();
    }

    // ================================ Admin Management ===================================
    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    // ================================ Movie Management ===================================
    public boolean addMovieToDB(Movie movie) {
        return movieDao.addMovieToDB(movie.getTitle(), movie.getDescription(), movie.getDirector(), movie.getGenre(),
                              movie.getDuration(), movie.getPrice(), movie.isVisibility(), movie.isAvailability());

    }


    @Override
    public User logIn(String userName, String password) {
        return null;
    }
}
