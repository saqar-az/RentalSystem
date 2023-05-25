

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Movie {
    static boolean appendMovie = false;
    private String director, cast,title, genre, id;
    private Date releaseDate;
    private boolean isAvailable;
    static ArrayList<String> customersIdCheck = new ArrayList<String>();
    static ArrayList<String> rentalsIdCheck = new ArrayList<String>();
    static ArrayList<String> moviesIdCheck = new ArrayList<String>();

    public Movie(String title, String genre, Date releaseDate, String id, String director, String cast) {
        this.director = director;
        this.cast = cast;
        boolean let = false;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        if (!moviesIdCheck.contains(id) && !customersIdCheck.contains(id) && !rentalsIdCheck.contains(id)) {
            let = false;
            this.id = id;
            moviesIdCheck.add(id);
        } else {
            System.out.println("This ID has already been taken");
            let = true;
        }
        while (let) {
            System.out.print("Enter another ID : ");
            Scanner input = new Scanner(System.in);
            id = input.nextLine();
            if (!moviesIdCheck.contains(id) && !customersIdCheck.contains(id) && !rentalsIdCheck.contains(id)) {
                this.id = id;
                moviesIdCheck.add(id);
                let = false;
            } else {
                System.out.println("This ID has already been taken");
            }
        }
    }

    boolean getIsAvailable() {
        return this.isAvailable;
    }

    void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    Date getReleaseDate() {
        return this.releaseDate;
    }

    String getId() {
        return this.id;
    }

    String getTitle() {
        return this.title;
    }

    String getGenre() {
        return this.genre;
    }

    public String getAllInformation(){
        return "Title : " + this.title + ", Director : " + this.director + ", Cast : " + this.cast +
                ", Genre : " + this.genre + ", Release Date :  " + this.releaseDate + " ,ID : " + this.id;
    }

    void remove(Movie movie) {
        if (!RentalStore.movies.contains(movie)) {
            System.out.println("We don't even have this movie");
        } else {
            if (!movie.getIsAvailable()) {
                RentalStore.movies.remove(movie);
                System.out.println("Successfully Removed " + movie.title);
            }
        }
    }

    void rentalFee(Movie movie, Customer customer, Date returndate) {
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

    void add(Movie movie) {
        if (!RentalStore.movies.contains(movie)) {
            RentalStore.movies.add(movie);
            System.out.println("successfully Added " + movie.title);
        } else {
            System.out.println("We already have this movie");
        }
    }
    void rentMovie(Movie movie, Customer customer) {
        if (customer.rentalMovies.contains(movie)) {
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
        if (customer.rentalMovies.contains(movie)) {

            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getMovieID().contains(movie.id)){
                    Date date =customer.rentals.get(i).setReturnDate();
                    movie.rentalFee(movie, customer,date);

                }
            }
            customer.rentalMovies.remove(movie);
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
}


