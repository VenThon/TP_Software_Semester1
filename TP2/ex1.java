import java.util.Scanner;
public class ex1{
    public static void main(String[] args) {
     Scanner mytest=new Scanner(System.in);
     String name;
     System.out.print("Ener your full name: ");
     name=mytest.nextLine();
     System.out.println("Hello " + name);
    }
}