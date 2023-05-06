import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Book extends Item {
    final private String author, publisher;
    static boolean appendBook=false;

    Book(String title, String genre, Date releaseDate, String id, String author, String publisher) {
        super(title, genre, releaseDate, id);
        this.author = author;
        this.publisher = publisher;
    }

    String getAuthor() {
        return this.author;
    }

    String getPublisher() {
        return this.publisher;
    }

    @Override
    String getAllInformation() {
        return "title : " + this.title + " , Genre : " + this.author + " , Publisher : " + this.publisher + " , Release Date : " + this.releaseDate+" , ID : "+this.id;
    }

    @Override
    void remove(Item item) {
        Book book = (Book) item;
        if (!RentalStore.books.contains(book)) {
            System.out.println("We don't even have this book");
        } else {
            if (!book.getIsAvailable()) {
                RentalStore.books.remove(book);
                System.out.println("Successfully Removed "+book.title);
            }
        }
    }
    @Override
    void add(Item item) {
        Book book = (Book) item;
        if (!RentalStore.books.contains(book)) {
            RentalStore.books.add(book);
            System.out.println("successfully Added " + book.title);
        } else {
            System.out.println("We already have this book");
        }
    }

    void rentBook(Book book,Customer customer){
        if (customer.rentals.contains(book)) {
            System.out.println("You already have this book");
        } else if (book.getIsAvailable()) {
            System.out.println("This book has been borrowed by someone else");
        } else if (!book.getIsAvailable()) {
            StringBuilder a = new StringBuilder(book.id);
            StringBuilder b = new StringBuilder(customer.getId());
            String id = String.valueOf(a.append(b));
            appendBook=true;
            Rental rental = new Rental(book, customer, id);
        }
    }
    void returnBook(Book book,Customer customer){
        if (customer.rentalItems.contains(book)) {

            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getMovieID().contains(book.id)){
                    Date date =customer.rentals.get(i).setReturnDate();
                    book.rentalFee(book, customer,date);
                }
            }
            customer.rentalItems.remove(book);
            for (int i = 0; i < customer.rentals.size(); i++) {
                if (customer.rentals.get(i).getMovieID().contains(book.id)) {
                    customer.rentals.remove(i);
                    break;
                }
            }
            book.setIsAvailable(false);
            System.out.println("Thanks " + customer.getName() + " for returning " + book.title);
        } else {
            System.out.println("You don't have this movie to return it");
        }
    }
    @Override
    void rentalFee(Item item, Customer customer, Date returndate) {
        Book book = (Book) item;
        for (int i = 0; i < customer.rentals.size(); i++) {
            if (customer.rentals.get(i).getMovieID().contains(book.id)) {
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
