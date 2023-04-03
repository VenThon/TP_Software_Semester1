import java.util.Scanner;

public class ex5 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Calculating duration of travel from ITC to airport.");
        System.out.println("Ennter traffic jam factor (in percentage [0-100]):50");
        System.out.print("Invalid of persentage: ");
        double percentage=sc.nextDouble();
        double result=(7/((double)(100-percentage)/100*30))*3600;
        
        int h,m,s;
        h=(int)(result/3600);
        m=(int)(result%3600)/60;
        s=(int)(result%60);
        System.out.printf("Travelling duration= %02d:%02d:%02d",h,m,s);

    }
}
