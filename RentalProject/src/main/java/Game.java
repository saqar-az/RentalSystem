import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
public class Game extends Item {
    final private String platform, publisher;
    Game(String title, String genre, String releaseDate, int id, String platform, String publisher) {
        super(title, genre, releaseDate, id);
        this.platform = platform;
        this.publisher = publisher;
        RentalStore.allGames.add(this);
    }
    String getPlatform() {
        return this.platform;
    }
    String getPublisher() {
        return this.publisher;
    }
    String getAllInformation() {

        return "Title : " + this.title + " ,Genre : " + this.genre + " , Platform : " + this.publisher + " , Publisher : " + this.publisher + " , Release Date : " + this.releaseDate +" , ID : "+this.id;
    }
    void returnGame(Game game, Customer customer) {
        if (customer.rentals.contains(game)) {
            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getId()==(game.id)){
                    //Date date =customer.rentals.get(i).setReturnDate();
                    Date date=new Date();
                    game.rentalFee(game, customer,date);

                }
            }
            customer.rentals.remove(game);
            for (int i = 0; i < customer.rentals.size(); i++) {
                if (customer.rentals.get(i).getId()==(game.id)) {
                    customer.rentals.remove(i);
                    break;
                }
            }
            game.setIsAvailable(true);
            System.out.println("Thanks " + customer.getName() + " for returning " + game.title);
        } else {
            System.out.println("You don't have this movie to return it");
        }
    }
    void rentalFee(Item item, Customer customer, Date returndate) {
        Game game = (Game) item;
        for (int i = 0; i < customer.rentals.size(); i++) {
            if (customer.rentals.get(i).getId()==(game.id)) {
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
