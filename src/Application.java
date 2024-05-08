import dao.MovieDao;
import dao.MovieTheaterDao;
import dao.TheaterDao;
import model.Movie;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        MovieDao movieDao = new MovieDao();
        TheaterDao theaterDao = new TheaterDao();

//        List<Theater> theaters = theaterDao.searchTheaterByName("Galaxy", Role.ADMIN);

//


        List<Movie> movies = movieDao.getAllMovies();
        for(Movie movie : movies) {
            System.out.println(movie.getId() + ". " + movie.getTitle());
        }

        movieDao.deleteMovieFromDB(movies.get(movies.size() - 1).getId());

    }

}