import java.util.Scanner;
public class ex3 {
  public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);
    System.out.println("Calculating equation 1/x=1/y+1/z;");
    System.out.print("Enter the value of number y: ");
    float y=sc.nextFloat();
    System.out.print("Enter the value of number z: ");
    float z=sc.nextFloat();
    float x=(y*z)/(y+z);
    System.out.printf(" Results x= %f ",x);
  }  
}
