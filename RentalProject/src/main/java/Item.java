import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Item {
    protected String title, genre, id;
    protected Date releaseDate;
    protected boolean isAvailable;
    static ArrayList<String> customersIdCheck = new ArrayList<String>();
    static ArrayList<String> rentalsIdCheck = new ArrayList<String>();
    static ArrayList<String> itemsIdCheck = new ArrayList<String>();

    Item(String title, String genre, Date releaseDate, String id) {
        boolean let = false;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        if (!itemsIdCheck.contains(id) && !customersIdCheck.contains(id) && !rentalsIdCheck.contains(id)) {
            let = false;
            this.id = id;
            itemsIdCheck.add(id);
        } else {
            System.out.println("This ID has already been taken");
            let = true;
        }
        while (let) {
            System.out.print("Enter another ID : ");
            Scanner input = new Scanner(System.in);
            id = input.nextLine();
            if (!itemsIdCheck.contains(id) && !customersIdCheck.contains(id) && !rentalsIdCheck.contains(id)) {
                this.id = id;
                itemsIdCheck.add(id);
                let = false;
            } else {
                System.out.println("This ID has already been taken");
            }
        }
    }

    boolean getIsAvailable() {
        return this.isAvailable;
    }

    void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    Date getReleaseDate() {
        return this.releaseDate;
    }

    String getId() {
        return this.id;
    }

    String getTitle() {
        return this.title;
    }

    String getGenre() {
        return this.genre;
    }

    abstract String getAllInformation();

    abstract void remove(Item item);

    abstract void rentalFee(Item item, Customer customer, Date returnDate);

    abstract void add(Item item);
}



