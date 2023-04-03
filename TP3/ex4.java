import java.util.Scanner;
public class ex4 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Program for converting money in riels to dollare.");
        System.out.println("Convert rate is: 1$ USD=4000 riels");
        System.out.print("Input money in riels: ");
        float y=sc.nextInt();
        float s= y/4000;
        System.out.printf("%.0f riels = %.2f USD",y,s);
    }
}
