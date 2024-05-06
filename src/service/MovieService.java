package service;

import dao.MovieDao;
import model.Movie;
import java.util.List;

public class MovieService {
    private MovieDao movieDao = new MovieDao();

    public List<Movie> getAllMovie() {
        return movieDao.getAllMovies();
    }
}
