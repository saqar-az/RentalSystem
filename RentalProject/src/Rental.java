
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Rental extends RentalStore {
    private String id;
    private Customer customer;
    private Movie movie;
    private Date rentalDate;
    private Date returnDate;

    Rental(Movie movie, Customer customer, String id) {
        boolean let = false;
        this.movie = movie;
        this.customer = customer;
        if (Movie.appendMovie) {
            while (true) {
                if (!Movie.moviesIdCheck.contains(id) && !Movie.rentalsIdCheck.contains(id) && !Movie.customersIdCheck.contains(id)) {
                    this.id = id;
                    Movie.rentalsIdCheck.add(id);
                    Movie.appendMovie = false;
                    let = true;
                    break;
                } else {
                    String x = "0";
                    StringBuilder a = new StringBuilder(id);
                    id = String.valueOf(a.append(x));
                }
            }
        } else {
            while (!let) {
                if (!Movie.moviesIdCheck.contains(id) && !Movie.rentalsIdCheck.contains(id) && !Movie.customersIdCheck.contains(id)) {
                    this.id = id;
                    Movie.rentalsIdCheck.add(id);
                    let = true;
                } else {
                    System.out.println("This ID has already been taken");
                    System.out.print("Enter another ID : ");
                    Scanner input = new Scanner(System.in);
                    id = input.nextLine();
                }
            }
        }
        Date rentalDate = new Date();
        this.rentalDate = rentalDate;
        movie.setIsAvailable(true);
        System.out.println("Thanks " + customer.getName() + " for borrowing " + movie.getTitle());
        customer.rentalMovies.add(movie);
        customer.rentals.add(this);
    }

    String getId() {
        return this.id;
    }

    String getCustomer() {
        return this.customer.getAllInformation();
    }

    String getMovie() {
        return this.movie.getAllInformation();
    }

    String getMovieID() {
        return this.movie.getId();
    }

    Date getRentalDate() {
        return this.rentalDate;
    }

    Date getReturnDate() {
        return this.returnDate;
    }

    Date setReturnDate() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Setting return time");
        System.out.println("1.now");
        System.out.println("2.set yourself");
        System.out.print("Enter : ");
        int b=scanner.nextInt();
        switch (b){
            case 1:
                LocalDate x =LocalDate.now();
                Date a=Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant());
                this.returnDate = a;
                return a;
            case 2:
                System.out.print("Enter year : ");
                int year=scanner.nextInt();
                System.out.print("Enter month : ");
                int month=scanner.nextInt();
                System.out.print("Enter day : ");
                int day=scanner.nextInt();
                LocalDate c=LocalDate.of(year,month,day);
                Date i=Date.from(c.atStartOfDay(ZoneId.systemDefault()).toInstant());
                this.returnDate = i;
                return i;
            case 3:
                System.out.println("invalid input!");
                break;
        }
        return null;
    }

    void calculateLateFee(Movie movie, Customer customer) {
        if (!movies.contains(movie)) {
            System.out.println("We don't have this movie!");
        } else {
            if (customer.rentalMovies.contains(movie)) {
                movie.rentalFee(movie, customer, rentalDate);
            } else {
                System.out.println("You don't even have this movie");
            }
        }
    }
}
