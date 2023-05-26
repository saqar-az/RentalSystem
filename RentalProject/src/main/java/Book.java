import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
public class Book extends Item {
    final private String author, publisher;
    Book(String title, String genre, String releaseDate, int id, String author, String publisher) {
        super(title, genre, releaseDate, id);
        this.author = author;
        this.publisher = publisher;
        RentalStore.allBooks.add(this);
    }
    String getAuthor() {
        return this.author;
    }
    String getPublisher() {
        return this.publisher;
    }
    String getAllInformation() {
        return "title : " + this.title + " , Genre : " + this.author + " , Publisher : " + this.publisher + " , Release Date : " + this.releaseDate+" , ID : "+this.id;
    }
    void returnBook(Book book,Customer customer){
        if (customer.rentals.contains(book)) {

            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getId()==(book.id)){
                    //Date date =customer.rentals.get(i).setReturnDate();
                    Date date=new Date();
                    book.rentalFee(book, customer,date);
                }
            }
            customer.rentals.remove(book);
            for (int i = 0; i < customer.rentals.size(); i++) {
                if (customer.rentals.get(i).getId()==(book.id)) {
                    customer.rentals.remove(i);
                    break;
                }
            }
            book.setIsAvailable(true);
            System.out.println("Thanks " + customer.getName() + " for returning " + book.title);
        } else {
            System.out.println("You don't have this movie to return it");
        }
    }
    void rentalFee(Item item, Customer customer, Date returndate) {
        Book book = (Book) item;
        for (int i = 0; i < customer.rentals.size(); i++) {
            if (customer.rentals.get(i).getId()==(book.id)) {
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
