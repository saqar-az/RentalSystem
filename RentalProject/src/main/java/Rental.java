import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Rental extends RentalStore {
    private String id;
    private Customer customer;
    private Movie movie;
    private Book book;
    private Game game;
    private Date rentalDate;
    private Date returnDate;

    Rental(Movie movie, Customer customer, String id) {
        boolean let = false;
        this.movie = movie;
        this.customer = customer;
        if (Movie.appendMovie) {
            while (true) {
                if (!Item.itemsIdCheck.contains(id) && !Item.rentalsIdCheck.contains(id) && !Item.customersIdCheck.contains(id)) {
                    this.id = id;
                    Item.rentalsIdCheck.add(id);
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
                if (!Item.itemsIdCheck.contains(id) && !Item.rentalsIdCheck.contains(id) && !Item.customersIdCheck.contains(id)) {
                    this.id = id;
                    Item.rentalsIdCheck.add(id);
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
        System.out.println("Thanks " + customer.getName() + " for borrowing " + movie.title);
        customer.rentalItems.add(movie);
        customer.rentals.add(this);
    }

    Rental(Game game, Customer customer, String id) {
        boolean let = false;
        this.game = game;
        this.customer = customer;
        if (Game.appendGame) {
            while (true) {
                if (!Item.itemsIdCheck.contains(id) && !Item.rentalsIdCheck.contains(id) && !Item.customersIdCheck.contains(id)) {
                    this.id = id;
                    Item.rentalsIdCheck.add(id);
                    Game.appendGame = false;
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
                if (!Item.itemsIdCheck.contains(id) && !Item.rentalsIdCheck.contains(id) && !Item.customersIdCheck.contains(id)) {
                    this.id = id;
                    Item.rentalsIdCheck.add(id);
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
        game.setIsAvailable(true);
        System.out.println("Thanks " + customer.getName() + " for borrowing " + game.title);
        customer.rentalItems.add(game);
        customer.rentals.add(this);
    }

    Rental(Book book, Customer customer, String id) {
        boolean let = false;
        this.book = book;
        this.customer = customer;
        if (Book.appendBook) {
            while (true) {
                if (!Item.itemsIdCheck.contains(id) && !Item.rentalsIdCheck.contains(id) && !Item.customersIdCheck.contains(id)) {
                    this.id = id;
                    Item.rentalsIdCheck.add(id);
                    Book.appendBook = false;
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
                if (!Item.itemsIdCheck.contains(id) && !Item.rentalsIdCheck.contains(id) && !Item.customersIdCheck.contains(id)) {
                    this.id = id;
                    Item.rentalsIdCheck.add(id);
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
        book.setIsAvailable(true);
        System.out.println("Thanks " + customer.getName() + " for borrowing " + book.title);
        customer.rentalItems.add(book);
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

    String getGame() {
        return this.game.getAllInformation();
    }

    String getGameID() {
        return this.game.id;
    }

    String getBook() {
        return this.book.getAllInformation();
    }

    String getBookID() {
        return this.book.id;
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

    void calculateLateFee(Item item, Customer customer) {
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            if (!movies.contains(movie)) {
                System.out.println("We don't have this movie!");
            } else {
                if (customer.rentalItems.contains(movie)) {
                    movie.rentalFee(item, customer, rentalDate);
                } else {
                    System.out.println("You don't even have this movie");
                }
            }
        } else if (item instanceof Book) {
            Book book = (Book) item;
            if (!books.contains(book)) {
                System.out.println("We don't have this book!");
            } else {
                if (customer.rentalItems.contains(book)) {
                    book.rentalFee(item, customer, rentalDate);
                } else {
                    System.out.println("You don't even have this book");
                }
            }
        } else if (item instanceof Game) {
            Game game = (Game) item;
            if (!games.contains(game)) {
                System.out.println("We don't have this game");
            } else {
                if (customer.rentalItems.contains(game)) {
                    game.rentalFee(item, customer, rentalDate);
                } else {
                    System.out.println("You don't even have this game");
                }
            }
        }
    }
}
