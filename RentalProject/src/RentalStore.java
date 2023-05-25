
import java.util.ArrayList;
import java.util.List;

public class RentalStore {
    static List<Customer> customers = new ArrayList<Customer>();
    static List<Movie> movies = new ArrayList<Movie>();

    void register(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            System.out.println("Thanks " + customer.getName() + " for joining");
        } else {
            System.out.println(customer.getName() + " You already have an account!");
        }
    }

    public void add(Movie movie) {
        movie.add(movie);
    }

    void remove(Movie movie) {
        movie.remove(movie);
    }

    void rentMovie(Movie movie, Customer customer) {
        movie.rentMovie(movie, customer);
    }

    void getAvailableMovies() {
        int count = 0, k = 0;
        for (int i = 0; i < movies.size(); i++) {
            if (!movies.get(i).getIsAvailable()) {
                count++;
                k++;
                System.out.print(count + " ");
                System.out.println(movies.get(i).getAllInformation());
            }
        }
        if (k == 0) {
            System.out.println("The List is Empty!");
        }
    }

    void getCustomerById(String id) {
        int k = 0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(id)) {
                k++;
                System.out.println(customers.get(i).getAllInformation());
                break;
            }
        }
        if (k == 0) {
            System.out.println("Nothing found!");
        }
    }

    void getById(String id) {
        int k = 0;
        for (int j = 0; j < Movie.moviesIdCheck.size(); j++) {
            if (Movie.moviesIdCheck.get(j).equals(id)) {
                k++;
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getId().equals(id)) {
                        System.out.println(movies.get(i).getAllInformation());
                        break;
                    }
                }
            }
        }
    }
    void returnMovie (Movie movie, Customer customer){
        movie.returnMovie(movie, customer);
    }
}