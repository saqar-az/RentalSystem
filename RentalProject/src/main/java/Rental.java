import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
public class Rental extends RentalStore {

    private int id;
    private  Item item;
    private Customer customer;
    private final Date rentalDate;
    private Date returnDate;
    Rental(Item item,Customer customer,int id){
        this.id=id;
        item.setIsAvailable(false);
        this.item=item;
        this.rentalDate=new Date();
        this.customer=new Customer(customer.getName(),customer.getEmail(),customer.getPhone(),customer.getAddress(),customer.getId());
    }
    int getId() {
        return this.id;
    }
    String getCustomer() {
        return this.customer.getAllInformation();
    }
    Date getRentalDate() {
        return this.rentalDate;
    }
    Date getReturnDate() {
        return this.returnDate;
    }
    String getItem(){
        return this.item.getTitle();
    }
    Date setReturnDate() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Setting return time");
        System.out.println("1.now");
        System.out.println("2.set yourself");
        System.out.print("Enter : ");
        int b=scanner.nextInt();
        switch (b) {
            case 1 -> {
                LocalDate x = LocalDate.now();
                Date a = Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant());
                this.returnDate = a;
                return a;
            }
            case 2 -> {
                System.out.print("Enter year : ");
                int year = scanner.nextInt();
                System.out.print("Enter month : ");
                int month = scanner.nextInt();
                System.out.print("Enter day : ");
                int day = scanner.nextInt();
                LocalDate c = LocalDate.of(year, month, day);
                Date i = Date.from(c.atStartOfDay(ZoneId.systemDefault()).toInstant());
                this.returnDate = i;
                return i;
            }
            case 3 -> System.out.println("invalid input!");
        }
        return null;
    }

    void calculateLateFee(Item item, Customer customer) {
        if (item instanceof Movie movie) {
            if (!allMovies.contains(movie)) {
                System.out.println("We don't have this movie!");
            } else {
                if (customer.rentals.contains(movie)) {
                    movie.rentalFee(item, customer, rentalDate);
                } else {
                    System.out.println("You don't even have this movie");
                }
            }
        } else if (item instanceof Book book) {
            if (!allBooks.contains(book)) {
                System.out.println("We don't have this book!");
            } else {
                if (customer.rentals.contains(book)) {
                    book.rentalFee(item, customer, rentalDate);
                } else {
                    System.out.println("You don't even have this book");
                }
            }
        } else if (item instanceof Game game) {
            if (!allGames.contains(game)) {
                System.out.println("We don't have this game");
            } else {
                if (customer.rentals.contains(game)) {
                    game.rentalFee(item, customer, rentalDate);
                } else {
                    System.out.println("You don't even have this game");
                }
            }
        }
    }
}
