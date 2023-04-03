import java.util.Scanner;
public class ex4 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Find counting the number of hundreds.");
        System.out.print("Enter the value of positive number: ");
        int n=sc.nextInt();
        int p=n/100;
        System.out.printf("There are %d hundred in number %d.",p,n);
    }
}
