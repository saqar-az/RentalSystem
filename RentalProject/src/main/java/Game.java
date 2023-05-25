import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Game extends Item {
    final private String platform, publisher;
    static boolean appendGame=false;

    Game(String title, String genre, Date releaseDate, String id, String platform, String publisher) {
        super(title, genre, releaseDate, id);
        this.platform = platform;
        this.publisher = publisher;
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

    @Override
    void remove(Item item) {
        Game game = (Game) item;
        if (!RentalStore.games.contains(game)) {
            System.out.println("We don't even have this game");
        } else {
            if (!game.getIsAvailable()) {
                RentalStore.games.remove(game);
                System.out.println("Successfully Removed "+game.title);
            }
        }
    }
    @Override
    void add(Item item) {
        Game game = (Game) item;
        if (!RentalStore.games.contains(game)) {
            RentalStore.games.add(game);
            System.out.println("successfully Added " + game.title);
        } else {
            System.out.println("We already have this game");
        }
    }

    void rentGame(Game game,Customer customer){
        if (customer.rentals.contains(game)) {
            System.out.println("You already have this game");
        } else if (game.getIsAvailable()) {
            System.out.println("This game has been borrowed by someone else");
        } else if (!game.getIsAvailable()) {
            StringBuilder a = new StringBuilder(game.id);
            StringBuilder b = new StringBuilder(customer.getId());
            String id = String.valueOf(a.append(b));
            appendGame=true;
            Rental rental = new Rental(game, customer, id);
        }
    }
    void returnGame(Game game, Customer customer) {
        if (customer.rentalItems.contains(game)) {

            for(int i=0;i<customer.rentals.size();i++){
                if(customer.rentals.get(i).getMovieID().contains(game.id)){
                    Date date =customer.rentals.get(i).setReturnDate();
                    game.rentalFee(game, customer,date);

                }
            }
            customer.rentalItems.remove(game);
            for (int i = 0; i < customer.rentals.size(); i++) {
                if (customer.rentals.get(i).getMovieID().contains(game.id)) {
                    customer.rentals.remove(i);
                    break;
                }
            }
            game.setIsAvailable(false);
            System.out.println("Thanks " + customer.getName() + " for returning " + game.title);
        } else {
            System.out.println("You don't have this movie to return it");
        }
    }
    @Override
    void rentalFee(Item item, Customer customer, Date returndate) {
        Game game = (Game) item;
        for (int i = 0; i < customer.rentals.size(); i++) {
            if (customer.rentals.get(i).getMovieID().contains(game.id)) {
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
