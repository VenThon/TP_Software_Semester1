import java.util.Scanner;
public class ex5 {
    public static void main(String[] args) {
       Scanner sc= new Scanner(System.in);
       System.out.println("Program for guessing your luckiness.");
       System.out.print("Enter a positive number: ");
       int n=sc.nextInt();
       int p=n+1;
       System.out.printf(" I got %d. I am luckier.",p);
    }
}
