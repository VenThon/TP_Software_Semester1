package BookClass;
import java.util.Scanner;
public class TestBook {
    public static void main(String[] args) throws Exception {
        Book book =new Book();
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                book.menu();
                choice= Integer.parseInt(sc.nextLine());
                if(choice==3){
                    book.dataInput();
                }else if(choice==1){
                    book.searchBooksName();
                }else if(choice==2){
                    book.checkBookAvailable();
                }else if(choice==4){
                    while (true) {
                        System.out.println("1.Increase 2.Decrease");
                        choice= Integer.parseInt(sc.nextLine());
                        if(choice==1){
                            book.increaseNumberBook();
                            break;
                        }else if(choice==2){
                            book.decreaseNumberBook();
                            break;
                        }else{
                            System.out.println("Please try again");
                        }
                    }
                }else if(choice == 5)
                    book.borrowBook();
                else if(choice==6){
                    book.displayOutput();
                }else if(choice==7) break;
            } catch (Exception e) {
                System.out.println("Please input Integer");
            }
        }
    }
}

