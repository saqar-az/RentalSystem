import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
public class Movie extends Item {
    final private String director, cast;
    public Movie(String title, String genre, String releaseDate, int id, String director, String cast) {
        super(title, genre, releaseDate, id);
        this.director = director;
        this.cast = cast;
        super.setIsAvailable(false);
        RentalStore.allMovies.add(this);
    }
    String getDirector() {
        return this.director;
    }
    String getCast() {
        return this.cast;
    }
    String getAllInformation() {
        return "Title : " + this.title + ", Director : " + this.director + ", Cast : " + this.cast + ", Genre : " + this.genre + ", Release Date :  " + this.releaseDate + " ,ID : " + this.id;
    }
    void returnMovie(Movie movie, Customer customer) {
        if (customer.rentals.contains(movie)) {
            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getId()==movie.id){
                    //Date date =customer.rentals.get(i).setReturnDate();
                    Date date=new Date();
                    movie.rentalFee(movie, customer,date);

                }
            }
            customer.rentals.remove(movie);
            for (int i = 0; i < customer.rentals.size(); i++) {
                if (customer.rentals.get(i).getId()==(movie.id)) {
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

    void rentalFee(Item item, Customer customer, Date returndate) {
        Movie movie = (Movie) item;
        for (int i = 0; i < customer.rentals.size(); i++) {
            if (customer.rentals.get(i).getId()==(movie.id)) {
                //Date before = customer.rentals.get(i).getRentalDate();
                Date before=new Date();
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
