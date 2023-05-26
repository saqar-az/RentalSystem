import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson=new Gson();
        Reader reader=new FileReader("C:\\Users\\s\\Desktop\\Rental Clone\\RentalSystem.Saqar\\RentalProject\\src\\test\\TestYourFork.json");
        AllModules allModulesList=gson.fromJson(reader,new TypeToken<AllModules>(){
        }.getType());

        Customer Joshn=allModulesList.getCustomers().get(0);
        Customer Emily=allModulesList.getCustomers().get(1);
        Customer Brown=allModulesList.getCustomers().get(2);

        RentalStore.allBooks=allModulesList.getBooks();
        RentalStore.allGames=allModulesList.getGames();
        RentalStore.allMovies=allModulesList.getMovies();
        RentalStore.allCustomers=allModulesList.getCustomers();

        for(Item tempItem : allModulesList.getBooks()){
            if(tempItem.id==3){
                RentalStore.rentItem(tempItem,Joshn);
            }
            else if (tempItem.id==6){
                RentalStore.rentItem(tempItem,Joshn);
            }
        }
        for(Item tempItem : allModulesList.getBooks()){
            if(tempItem.id==1){
                RentalStore.rentItem(tempItem,Emily);
            }
            else if (tempItem.id==7){
                RentalStore.rentItem(tempItem,Emily);
            }
        }
        for(Item tempItem : allModulesList.getBooks()){
            if(tempItem.id==9){
                RentalStore.rentItem(tempItem,Brown);
            }
            else if (tempItem.id==4){
                RentalStore.rentItem(tempItem,Brown);
            }
        }

        reader.close();
        Gson writing=new Gson();
        String json=writing.toJson(allModulesList);
        String filepath="C:\\Users\\s\\Desktop\\Rental Clone\\RentalSystem.Saqar\\RentalProject\\src\\test\\TestYourFork.json";
        try {
            FileWriter writer=new FileWriter(filepath);
            writer.write(json);
            writer.close();
            System.out.println("JSON data has been updated");

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
