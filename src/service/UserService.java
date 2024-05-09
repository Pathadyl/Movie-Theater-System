package service;

import dao.MovieDao;
import dao.TheaterDao;
import model.Movie;
import model.Theater;
import model.User;
import model.Role;

import java.util.ArrayList;
import java.util.List;

public abstract class UserService {
    private MovieDao movieDao = new MovieDao();
    private TheaterDao theaterDao = new TheaterDao();


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
        return movieDao.getMovieListByTheater(theater, role);
    }
    abstract public User logIn(String userName, String password);
}
