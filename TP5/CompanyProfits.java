import java.util.Scanner;
public class CompanyProfits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float sum=0;
        for(int i=1; i<=12; i++){
            System.out.printf("Profit for month %d# ",i);
            float number=sc.nextFloat();
            sum=sum+number;
        }
        System.out.printf("\nTotal profits for 12 months: %f", sum);
    }
}
