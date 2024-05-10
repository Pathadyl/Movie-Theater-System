import dao.*;
import model.*;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        MovieDao movieDao = new MovieDao();
        TheaterDao theaterDao = new TheaterDao();
        AdminDao adminDao = new AdminDao();
        MemberDao memberDao = new MemberDao();
        GuestDao guestDao = new GuestDao();
        ShowtimeDao showTimeDao = new ShowtimeDao();


        List<Movie> movies = movieDao.getAllMovies();
        for(Movie movie : movies) {
            System.out.println(movie.getId() + ". " + movie.getTitle());
        }

        List<Theater> theaters = theaterDao.getAllTheaters();
        for(Theater theater : theaters) {
            System.out.println(theater.getId() + ". " + theater.getName());
        }

//        Admin admin = adminDao.getAuthenticated("admin01", "admin01");
//        System.out.println(admin.getId() + ". " + admin.getUserName());
//
//        Member member = memberDao.getAuthenticated("member1", "abc123");
//        System.out.println("Id: " + member.getId());
//        System.out.println("Name: " + member.getName());
//        System.out.println("Phone: " + member.getPhone());
//        System.out.println("Email: " + member.getEmail());
//        System.out.println("Date of birth: " + member.getDob());
//        System.out.println("Reward point: " + member.getRewardPoint());
//
//        Guest guest = guestDao.createGuest();
//        System.out.println("Id: " + guest.getId());
//        System.out.println("Name: " + guest.getName());
//        System.out.println("Phone: " + guest.getPhone());
//        System.out.println("Email: " + guest.getEmail());


        List<Showtime> showtimes = showTimeDao.getShowTimeListByMovie(movies.get(0).getId());
        System.out.println("Movie " + movies.get(0).getId() + " has list of show times: ");
        for(Showtime showtime : showtimes) {
            System.out.println(showtime.getDate());
        }

    }

}