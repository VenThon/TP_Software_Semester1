import java.util.Scanner;
public class ex2 {
   public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println("Program for conerting time to seconds.");
    System.out.print("Input number of hours: ");
    int h=sc.nextInt();
    System.out.print("Input number of minutes: ");
    int m=sc.nextInt();
    System.out.print("Input number of second: ");
    int s=sc.nextInt();
    int sum=h*3600 + m*60 + s;
    System.out.printf("Number of seconds= %dx3600 + %dx60 + %d= %d",h,m,s,sum);
   } 
}
