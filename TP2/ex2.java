import java.util.Scanner;
public class ex2 {
    public static void main(String[] args) {
        Scanner myNumber= new Scanner(System.in);
        System.out.println("Calculating perimeter and Surface");
        System.out.print("Enter the value of width:  ");
        int width=myNumber.nextInt();
        System.out.print("Enter the value of height:  ");
        int height=myNumber.nextInt();
        int p=(height+width)*2;
        int s=width*height;
        System.out.printf(" Perimeter=(%d + %d)x2= %d m\n",width,height,p);
        System.out.printf(" Surface=%d x %d= %d m^2",width,height,s);

    }
}
