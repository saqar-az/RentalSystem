import java.util.Date;
public class Item {
    protected String title, genre,releaseDate;
    protected int id;
    protected boolean isAvailable;
    Item(String title, String genre, String releaseDate,int id) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.id=id;
        this.isAvailable=true;
    }

    boolean getIsAvailable() {
        return this.isAvailable;
    }

    void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    String getReleaseDate() {
        return this.releaseDate;
    }

    int getId() {
        return this.id;
    }

    String getTitle() {
        return this.title;
    }
    String getGenre() {
        return this.genre;
    }
}



