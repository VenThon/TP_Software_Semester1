import java.util.Scanner;
public class Ex1 {
    public static void main(String[] args) {
        int Num;
        Scanner sc=new Scanner(System.in);
        System.out.print("Input a Positive number:");
        while(!sc.hasNextInt()){
            System.out.print("Input only Positive number:");
            sc.next();
        }
        Num=sc.nextInt();
        while(Num<0){
            System.out.print("Input Positive number:");
            while(!sc.hasNextInt()){
                System.out.print("Input only Positive number:");
                sc.next();
            }
            Num=sc.nextInt();
        }

        for(int i=Num-1;i>0;i-=2){
            System.out.printf("%02d ",i);
        }
        for(int i=2;i<=Num;i+=2){
            System.out.printf("%02d ",i);
        }
        
   }
}
