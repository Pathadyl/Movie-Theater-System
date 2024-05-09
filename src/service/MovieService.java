package service;

import dao.MovieDao;
import model.Movie;
import java.util.List;

public class MovieService {
    private MovieDao movieDao = new MovieDao();

    public List<Movie> getAllMovie() {
        return movieDao.getAllMovies();
    }
    public void addMovie(String title, String description,
                         String director, String genre, String duration,
                         int price, boolean visibility, boolean availability){
        // Get data from submit request on GUI
        //title = text1.getString() ....
        movieDao.addMovie(title,description,director,genre,duration,price,visibility,availability);
    }
}
