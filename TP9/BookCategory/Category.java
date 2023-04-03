package BookCategory;
import java.util.ArrayList;
import BookClass.Book;
public class Category {
    private String name;
    private String description;
    private ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getBooks(){
        return this.books;
    }
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    };

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void inputData(){
        
    }
}
