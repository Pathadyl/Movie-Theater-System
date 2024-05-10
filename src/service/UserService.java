package service;

import dao.MovieDao;
import dao.TheaterDao;
import model.Movie;
import model.Theater;
import model.User;
import model.Role;

import java.util.List;

public abstract class UserService {
    private MovieDao movieDao;
    private TheaterDao theaterDao;
    private User user;

    public UserService(User user) {
        this.user = user;
        movieDao = new MovieDao();
        theaterDao = new TheaterDao();
    }

    public List<Movie> getAllMovie() {
        return movieDao.getAllMovies();
    }

    public List<Movie> searchMovieByTitle(String title, Role role) {
        return movieDao.searchMovieByTitle(title, role);
    }

    public List<Theater> searchTheaterByName(String name, Role role) {
        return theaterDao.searchTheaterByName(name, role);
    }

    public List<Movie> getMovieListByGenre(String genre, Role role){
        return movieDao.getMovieListByGenre(genre, role);
    }

    public List<Movie> getMovieListByTheater(Theater theater, Role role) {
        return movieDao.getMovieListByTheater(theater.getId(), role);
    }

    abstract public User logIn(String userName, String password);



    public MovieDao getMovieDao() {
        return movieDao;
    }
    public TheaterDao getTheaterDao() {
        return theaterDao;
    }
    public User getUser() {
        return user;
    }
}
