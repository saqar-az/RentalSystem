import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Movie extends Item {
    final private String director, cast;
    static boolean appendMovie = false;

    public Movie(String title, String genre, Date releaseDate, String id, String director, String cast) {
        super(title, genre, releaseDate, id);
        this.director = director;
        this.cast = cast;
        super.setIsAvailable(false);
    }

    String getDirector() {
        return this.director;
    }

    String getCast() {
        return this.cast;
    }

    @Override
    String getAllInformation() {
        return "Title : " + this.title + ", Director : " + this.director + ", Cast : " + this.cast + ", Genre : " + this.genre + ", Release Date :  " + this.releaseDate + " ,ID : " + this.id;
    }

    @Override
    void remove(Item item) {
        Movie movie = (Movie) item;
        if (!RentalStore.movies.contains(movie)) {
            System.out.println("We don't even have this movie");
        } else {
            if (!movie.getIsAvailable()) {
                RentalStore.movies.remove(movie);
                System.out.println("Successfully Removed " + movie.title);
            }
        }
    }

    @Override
    void add(Item item) {
        Movie movie = (Movie) item;
        if (!RentalStore.movies.contains(movie)) {
            RentalStore.movies.add(movie);
            System.out.println("successfully Added " + movie.title);
        } else {
            System.out.println("We already have this movie");
        }
    }

    void rentMovie(Movie movie, Customer customer) {
        if (customer.rentalItems.contains(movie)) {
            System.out.println("You already have this movie");
        } else if (movie.getIsAvailable()) {
            System.out.println("This movie has been borrowed by someone else");
        } else if (!movie.getIsAvailable()) {
            StringBuilder a = new StringBuilder(movie.id);
            StringBuilder b = new StringBuilder(customer.getId());
            String id = String.valueOf(a.append(b));
            appendMovie = true;
            Rental rental = new Rental(movie, customer, id);
        }
    }

    void returnMovie(Movie movie, Customer customer) {
        if (customer.rentalItems.contains(movie)) {

            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getMovieID().contains(movie.id)){
                    Date date =customer.rentals.get(i).setReturnDate();
                    movie.rentalFee(movie, customer,date);

                }
            }
            customer.rentalItems.remove(movie);
            for (int i = 0; i < customer.rentals.size(); i++) {
                if (customer.rentals.get(i).getMovieID().contains(movie.id)) {
                    customer.rentals.remove(i);
                    break;
                }
            }
            movie.setIsAvailable(false);
            System.out.println("Thanks " + customer.getName() + " for returning " + movie.title);
        } else {
            System.out.println("You don't have this movie to return it");
        }
    }

    @Override
    void rentalFee(Item item, Customer customer, Date returndate) {
        Movie movie = (Movie) item;
        for (int i = 0; i < customer.rentals.size(); i++) {
            if (customer.rentals.get(i).getMovieID().contains(movie.id)) {
                Date before = customer.rentals.get(i).getRentalDate();

                LocalDate x=before.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate c=returndate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long daysBtw = ChronoUnit.DAYS.between(x, c);

                if (daysBtw > 14) {
                    System.out.println("Your Rental fee is $" + (daysBtw-14)* 10);
                } else {
                    System.out.println("Your Rental fee is $0");
                }
                break;
            }

        }
    }
}
