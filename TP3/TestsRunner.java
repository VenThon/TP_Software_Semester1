import java.util.Scanner;
public class TestsRunner {
    public static void main(String[] args) {
       Scanner sc= new Scanner(System.in);
       int option;
       do{
        System.out.println("\n\n===========Menu========");
        System.out.println("1. Seconds to time");
        System.out.println("2. Time to seconds");
        System.out.println("3. Calling cost");
        System.out.println("4. Riels to dollar");
        System.out.println("5. Traveling Duration");
        System.out.println("0. Exit");
        System.out.print("Enter option you want: ");
        option=sc.nextInt();
        System.out.println("========================\n");
        switch(option){
            case 1:
                ex1.main(null);
                break;
            case 2:
                ex2.main(null);
                break;
            case 3:
                ex3.main(null);
                break;
            case 4:
                ex4.main(null);
                break;
            case 5:
                ex5.main(null);
            break;
            default:
            System.out.println("Exit");
        }
       }while(option !=0);

    }
}
