import java.util.ArrayList;

public class AllModules {
    public ArrayList<Game> games;
    public ArrayList<Book> books;
    public ArrayList<Movie> movies;
    public ArrayList<Customer> customers;

    public java.util.ArrayList<Game> getGames() {
        return games;
    }
    public java.util.ArrayList<Book> getBooks() {
        return books;
    }
    public java.util.ArrayList<Movie> getMovies() {
        return movies;
    }
    public java.util.ArrayList<Customer> getCustomers() {
        return customers;
    }
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
