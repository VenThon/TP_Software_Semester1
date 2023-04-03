import java.util.Scanner;
public class ex1{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Calculate of hours:Minutes:Second:");
        System.out.print("Input number of seconds: ");
        int y=sc.nextInt();
        int s=y%60;
        int m=(y%3600)/60;
        int h=y/3600;
        System.out.printf("Time corresponding to %d is %d:%d:%d.",y,h,m,s);
    }
}