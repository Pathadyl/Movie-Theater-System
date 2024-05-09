import dao.MovieDao;
import dao.TheaterDao;
import model.*;
import service.AdminService;
import service.MovieService;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        MovieDao movieDao = new MovieDao();
        TheaterDao theaterDao = new TheaterDao();

        List<Theater> theaters = theaterDao.searchTheaterByName("Galaxy", Role.ADMIN);
        List<Movie> movies = movieDao.getMovieListByGenre("Action", Role.CUSTOMER);
//        for(Theater theater : theaters) {
//            System.out.println("Theater " + theater.getName());
//        }

        for(Movie movie : movies) {
            System.out.println(movie.getTitle());
        }

//        MovieService newMovie = new MovieService();
//        newMovie.addMovie("Ackane", "A group of hero fight againts evil",
//                       "Riot", "Science fiction", "150", 45000, true, true);
    }

}