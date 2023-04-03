package BookClass;
import java.text.*;
import java.util.Date;
import java.util.*;

public class Book {
    private String title;
    private String description;
    private String category;
    private String isbn;
    private Author author ;
    private Date publishDate;
    private boolean available;
    private int quatity;
    private ArrayList<Book> books = new ArrayList<>();

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        if(getQuatity()!=0){
            this.available = available;
        }
    }

    public int getQuatity() {

        return this.quatity;
    }

    public void setQuatity(int quatity) {
        
            this.quatity = quatity;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public void addBooks(Book book) {
        Scanner sc = new Scanner(System.in);
        int p = 0;
        for (Book booklist : books) {
            if (book.getTitle().equalsIgnoreCase(booklist.getTitle())) {
                System.out.println("Please add more quantity:");
                booklist.setQuatity(Integer.parseInt(sc.nextLine()));
                p = 1;
            }
        }
        if (p != 1)
            this.books.add(book);
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        try {
            this.publishDate = publishDate;
        } catch (Exception e) {
            System.out.println("Wrong format");
        }
    }

    public Book() {

    }

    public void menu() {
        System.out.println("1.Search:");
        System.out.println("2. Check Available:");
        System.out.println("3.Create new Book:");
        System.out.println("4.Decrease/Increase number of books:");
        System.out.println("5.Borrow Book:");
        System.out.println("6.Display book:");
        System.out.println("7.Exit");
        System.out.print("Enter your choice");
    }

    public Book(String title, String description, String category, String isbn, Author author, Date publishDate) {
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setIsbn(isbn);
        setAuthor(author);
        setPublishDate(publishDate);
    }

    public void dataInput() throws Exception {
        Book book = new Book();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> name = new ArrayList<>();
        System.out.print("Input title:");
        book.setTitle(sc.nextLine());
        System.out.print("Input description:");
        book.setDescription(sc.nextLine());
        System.out.print("Input Category:");
        book.setCategory(sc.nextLine());
        System.out.print("Input isbn:");
        book.setIsbn(sc.nextLine());
        System.out.print("How many author:");
        int amount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < amount; i++) {
            System.out.print("Input author's name[" + i + "]:");
            name.add(sc.nextLine());
        }
        book.setAuthor(new Author(name));
        System.out.print("Input public date:");
        book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
        System.out.println("Input available(Y) or other not avialble:");
        String p = sc.nextLine();
        if (p.equalsIgnoreCase("y"))
            book.setAvailable(true);
        else
            book.setAvailable(false);
        System.out.println("Input quatity:");
        try {
            book.setQuatity(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("should be integer");
        }
        addBooks(book);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (blankExeption(title))
            this.title = title;
        else
            System.out.println("title cannot be null");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (blankExeption(description))
            this.description = description;
        else
            System.out.println("desciption cannot be null");
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        if (blankExeption(category))
            this.category = category;
        else
            System.out.println("category cannot be null");
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        if (blankExeption(isbn))
            this.isbn = isbn;
        else
            System.out.println("isbn cannot be null");
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public static boolean blankExeption(String variable) {
        if (variable.isBlank() || variable.isEmpty()) {
            System.out.println("Blank Empty");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String avaString;
        if (available) {
            avaString = "available";
        } else
            avaString = "not available";
        return "{ title:" + title + ",description:" + description + ",category:" + category + ",isbn:" + isbn
                + ",author:" + author.display() + ",publishDate:" + publishDate + ",avalaible:" + avaString +",Quantity:"+getQuatity()+ "}";
    }
    public void searchBooksName() throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.print("Search book:");
        
        Book p=searchBook(sc.nextLine());
        if(p!=null){
            System.out.println(p);
        }
    }
    public void decreaseNumberBook() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Increase number of book");
        System.out.print("Search book");
        Book p = searchBook(sc.nextLine());
        if (p != null) {
            System.out.println("How many u want to decrease");
            int r = Integer.parseInt(sc.nextLine());
            p.setQuatity(p.getQuatity() - r);
        } else
            System.out.println("There is no that book");
    }
    public void increaseNumberBook() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Increase number of book");
        System.out.print("Search book");
        Book p = searchBook(sc.nextLine());
        if (p != null) {
            System.out.println("How many u want to increase");
            int r = Integer.parseInt(sc.nextLine());
            p.setQuatity(p.getQuatity() + r);
        } else
            System.out.println("There is no that book");
    }
    public void borrowBook(){
        Scanner sc = new Scanner(System.in);
        for (Book book : books) {   
            if(book.isAvailable()){
             System.out.println(book+"borrowable");
            } 
         }
        System.out.println("Search title book to borrow:");
        String name = sc.nextLine();
        for (Book book : books) {   
            if(book.isAvailable()&&name.equalsIgnoreCase(book.getTitle())){
             System.out.println("you borrowed the book"+book);
             book.setQuatity(book.getQuatity()-1);
            } 
         }
    }
    public Book searchBook(String name) throws Exception {
        for (Book book : books) {
            if (name.equalsIgnoreCase(book.getTitle())||name.equalsIgnoreCase(book.getDescription())||name.equalsIgnoreCase(book.getCategory())
            ||name.equalsIgnoreCase(book.getIsbn())) {
                return book;
            }

        }
        return null;

    }

    public void checkBookAvailable() {
        for (Book book : books) {
            if (book.isAvailable())
                System.out.println(book);
        }
        if (books.isEmpty())
            System.out.println("there is no book right now!");
    }

    public void displayOutput() {

        for (Book book : books) {
            System.out.println(book);
        }
        if (books.isEmpty())
            System.out.println("There is no book right now!");
    }
}

