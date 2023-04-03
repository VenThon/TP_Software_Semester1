package BookClass;
import java.util.*;
import java.lang.*;
public class Author {
    private ArrayList<String> name = new ArrayList<>();

    public Author() {
    }

    public Author(ArrayList<String> name) {
        setName(name);
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        if (!name.isEmpty())
            this.name = name;
        else
            System.out.println("author cannot be null");

    }

    public String searchName(String nameslist) {
        for (String names : name) {
            if (names.equalsIgnoreCase(nameslist))
                return names;
        }
        return null;
    }

    public ArrayList<String> display() {
        return name;
    }

}
