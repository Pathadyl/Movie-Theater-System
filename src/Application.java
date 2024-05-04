import model.Admin;
import model.Movie;
import service.AdminService;
import service.MovieService;

import java.awt.geom.AffineTransform;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();

        List<Admin> admins = adminService.getAllAdmin();

        int count = 1;
        for (Admin admin: admins) {
            String header = "Admin" + count;
            System.out.println("======================== " + header + " ========================");
            System.out.println("- Id: " + admin.getId());
            System.out.println("- UserName: " + admin.getUserName());
            System.out.println("- Password: " + admin.getPassword());
            count++;
        }

//        MovieService newMovie = new MovieService();
//        newMovie.addMovie("Ackane", "A group of hero fight againts evil",
//                       "Riot", "Science fiction", "150", 45000, true, true);
    }


    public static void printStringInChunks(String str, int wordsPerLine) {
        String[] words = str.split("\\s+");

        int wordCount = 0;
        for (String word : words) {
            System.out.print(word + " ");
            wordCount++;

            if (wordCount == wordsPerLine) {
                System.out.println();
                wordCount = 0;
            }
        }
    }
}