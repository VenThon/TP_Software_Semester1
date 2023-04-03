import java.util.Scanner;
public class PrimeNumber2ToN {
    public static boolean isPrime(int num){
      boolean Prime= true;
      for(int i=2; i<num; i++) {
        if(num%i==0){
            Prime=false;
            break;
        }
      }
      return Prime;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number to list prime numbers from 2 to it: ");
        int number=sc.nextInt();
        for(int k=2; k<number; k++){
            if(isPrime(k)){
              System.out.print(k+"\t");  
            }
        }
    }
}
