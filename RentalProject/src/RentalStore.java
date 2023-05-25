import java.util.ArrayList;
import java.util.List;

public class RentalStore {
    static List<Customer> customers = new ArrayList<Customer>();
    static List<Item> movies = new ArrayList<Item>();
    static List<Book> books = new ArrayList<Book>();
    static List<Game> games = new ArrayList<Game>();

    void register(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            System.out.println("Thanks " + customer.getName() + " for joining");
        } else {
            System.out.println(customer.getName() + " You already have an account!");
        }
    }

    public void add(Item item) {
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            movie.add(item);
        } else if (item instanceof Book) {
            Book book = (Book) item;
            book.add(item);
        } else if (item instanceof Game) {
            Game game = (Game) item;
            game.add(item);
        }
    }

    void remove(Item item) {
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            movie.remove(item);
        } else if (item instanceof Book) {
            Book book = (Book) item;
            book.remove(item);

        } else if (item instanceof Game) {
            Game game = (Game) item;
            game.remove(item);
        }
    }

    void rentItem(Item item, Customer customer) {
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            movie.rentMovie(movie, customer);
        } else if (item instanceof Book) {
            Book book = (Book) item;
            book.rentBook(book, customer);
        } else if (item instanceof Game) {
            Game game = (Game) item;
            game.rentGame(game, customer);
        }
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

    void getAvailableGames() {
        int count = 0, k = 0;
        for (int i = 0; i < games.size(); i++) {
            if (!games.get(i).getIsAvailable()) {
                count++;
                k++;
                System.out.print(count + " ");
                System.out.println(games.get(i).getAllInformation());
            }
        }
        if (k == 0) {
            System.out.println("The List is Empty!");
        }
    }

    void getAvailableBooks() {
        int count = 0, k = 0;
        for (int i = 0; i < books.size(); i++) {
            if (!books.get(i).getIsAvailable()) {
                count++;
                k++;
                System.out.print(count + " ");
                System.out.println(books.get(i).getAllInformation());
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
        for (int j = 0; j < Item.itemsIdCheck.size(); j++) {
            if (Item.itemsIdCheck.get(j).equals(id)) {
                k++;
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getId().equals(id)) {
                        System.out.println(movies.get(i).getAllInformation());
                        break;
                    }
                }
                for (int i = 0; i < games.size(); i++) {
                    if (games.get(i).getId().equals(id)) {
                        System.out.println(games.get(i).getAllInformation());
                        break;
                    }
                }
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getId().equals(id)) {
                        System.out.println(books.get(i).getAllInformation());
                        break;
                    }
                }
            }
        }
        if (k == 0) {
            System.out.println("Nothing found!");
        }
    }

    void returnItem(Item item, Customer customer) {
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            movie.returnMovie(movie, customer);
        } else if (item instanceof Book) {
            Book book = (Book) item;
            book.returnBook(book, customer);
        } else if (item instanceof Game) {
            Game game = (Game) item;
            game.returnGame(game, customer);
        }
    }
}



