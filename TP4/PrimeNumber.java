import java.util.Scanner;
public class PrimeNumber{
   public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);
    System.out.print("\nInput number to check whether it is prime number: ");
    int num=sc.nextInt();
    if(num>0){
      int half=num/2;
      boolean isPrime=true;
      int i=2;
      for(; i<=half; i++){
         if(num%i==0){
            isPrime=false;
            break;
         }
      }if(isPrime){
         System.out.println(num + " is prime nnumber.");
      }else{
         System.out.printf("%d is not prime number, because it is divisble to %d.\n",num,i);
      }
    }else{
      System.out.println(num + " is not primme nnumber, because it is smaller nnumber 2.");
    }
 
   }
}  

