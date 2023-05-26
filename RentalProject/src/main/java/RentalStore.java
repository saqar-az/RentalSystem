import java.util.ArrayList;
import java.util.List;
public  class RentalStore {
    static List<Customer> allCustomers = new ArrayList<>();
    static List<Movie> allMovies = new ArrayList<>();
    static List<Book> allBooks = new ArrayList<>();
    static List<Game> allGames = new ArrayList<>();
    static public void add(Item item) {
        if (item instanceof Movie movie) {
            allMovies.add(movie);
        } else if (item instanceof Book book) {
            allBooks.add(book);
        } else if (item instanceof Game game) {
            allGames.add(game);
        }
    }

    static public void remove(Item item) {
        if (item instanceof Movie movie) {
            if (movie.getIsAvailable()) {
                allMovies.remove(item);
            }
        }else if (item instanceof Book book) {
            if (book.getIsAvailable()) {
                allBooks.remove(item);
            }
        }else if (item instanceof Game game) {
                    if (game.getIsAvailable()) {
                        allGames.remove(item);
                    }
                }
            }

    static public void rentItem(Item item, Customer customer) {
        if(allMovies.contains(item)){
           for (int i=0;i<allMovies.size();i++) {
             if (allMovies.get(i).getId() == item.getId()) {
                 if(allMovies.get(i).isAvailable==true){
                     int x = allMovies.get(i).id;
                     int y = customer.getId();
                     int z = Integer.valueOf(String.valueOf(x) + String.valueOf(y));
                     Rental rental = new Rental((Movie) allMovies.get(i),customer, z);
                     if(customer.rentals==null){
                         customer.rentals =new ArrayList<>();
                     }
                     if(!customer.rentals.contains(item)){
                         customer.rentals.add(allGames.get(i));
                         System.out.println("Thanks " + customer.getName() + " for borrowing " + allBooks.get(i).title);
                         allMovies.get(i).isAvailable=false;
                     }else {
                         System.out.println("You already have "+item.title);
                     }
                 }else {
                     System.out.println(allMovies.get(i).title+" is borrowed by some one else");
                 }
                 }
        }
         } else if (allBooks.contains(item)) {
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).id == item.id) {
                if(allBooks.get(i).isAvailable==true){
                    int x = allBooks.get(i).id;
                    int y = customer.getId();
                    int z = Integer.valueOf(String.valueOf(x) + String.valueOf(y));
                    Rental rental = new Rental((Book) allBooks.get(i), customer, z);
                    if(customer.rentals==null){
                        customer.rentals =new ArrayList<>();
                    }if(!customer.rentals.contains(item)){
                        customer.rentals.add(allGames.get(i) );
                        System.out.println("Thanks " + customer.getName() + " for borrowing " + allBooks.get(i).title);
                        allBooks.get(i).isAvailable=false;
                    }else {
                        System.out.println("You already have "+item.title);
                    }

                }else {
                    System.out.println(allBooks.get(i).title+" is borrowed by some one else");
                }
                }

            }
        } else if (allGames.contains(item)) {
              for (int i=0;i<allGames.size();i++){
                   if(allGames.get(i).getId()==item.getId()){
                       if(allGames.get(i).isAvailable==true){
                           int x = allGames.get(i).id;
                           int y = customer.getId();
                           int z = Integer.valueOf(String.valueOf(x) + String.valueOf(y));
                           Rental rental = new Rental((Game) allGames.get(i), customer, z);
                           if(customer.rentals==null){
                               customer.rentals =new ArrayList<>();
                           } if(!customer.rentals.contains(allGames.get(i))){
                               customer.rentals.add(allGames.get(i));
                               System.out.println("Thanks " + customer.getName() + " for borrowing " + allGames.get(i).title);
                               allGames.get(i).isAvailable=false;
                           }else{
                               System.out.println("You already have "+item.title);
                           }
                       }else {
                           System.out.println(allGames.get(i).title+" is borrowed by some one else");
                       }
                       }
             }
              }
            }
    static public void getAvailableMovies() {
        int count = 0, k = 0;
        for (int i = 0; i < allMovies.size(); i++) {
            if (allMovies.get(i).getIsAvailable()) {
                count++;
                k++;
                System.out.print(count + " ");
                System.out.println(allMovies.get(i).getAllInformation());
            }
        }
        if (k == 0) {
            System.out.println("The List is Empty!");
        }
    }

    static public void getAvailableGames() {
        int count = 0, k = 0;
        for (int i = 0; i < allGames.size(); i++) {
            if (allGames.get(i).getIsAvailable()) {
                count++;
                k++;
                System.out.print(count + " ");
                System.out.println(allGames.get(i).getAllInformation());
            }
        }
        if (k == 0) {
            System.out.println("The List is Empty!");
        }
    }

    static public void getAvailableBooks() {
        int count = 0, k = 0;
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getIsAvailable()) {
               count++;
                k++;
                System.out.print(count + " ");
                    System.out.println(allBooks.get(i).getAllInformation());
            }
        }
        if (k == 0) {
            System.out.println("The List is Empty!");
        }
    }

    static public void getCustomerById(int id) {
        int k = 0;
        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getId()==(id)) {
            //    k++;
                System.out.println(allCustomers.get(i).getAllInformation());
                break;
            }
        }
        if (k == 0) {
            System.out.println("Nothing found!");
        }
    }

    static public void getMovieByID(int id) {
        for (int i = 0; i < allMovies.size(); i++) {
            if (allMovies.get(i).getId() == id) {
                System.out.println(allMovies.get(i).getAllInformation());
                break;
            }

        }
    }
    static public void getBookById(int id){
            for (int i=0;i<allBooks.size();i++){
                if(allBooks.get(i).getId()==id){
                    System.out.println(allBooks.get(i).getAllInformation());
                    break;
                }
            }
        }
    static public void getGameByID(int id){
            for (int i=0;i<allGames.size();i++){
                if(allGames.get(i).getId()==id){
                    System.out.println(allGames.get(i).getAllInformation());
                    break;
                }
            }
        }
    static public void returnItem(Item item, Customer customer) {
        if (item instanceof Movie movie) {
            movie.returnMovie(movie, customer);
        } else if (item instanceof Book book) {
            book.returnBook(book, customer);
        } else if (item instanceof Game game) {
            game.returnGame(game, customer);
        }
    }
}



