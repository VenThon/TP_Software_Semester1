import java.util.Scanner;
public class OddNumber {
    public static void main(String[] args) {
        java.util.Scanner sc = new Scanner(System.in);
        System.out.print("Input odd number from 0 to : ");
        int number=sc.nextInt();
        for(int i=1; i<number; i=i+2){
            System.out.print(i+"\t");
        }
    }
}
