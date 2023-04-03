import java.util.Scanner;

public class EvenNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of A: ");
        int num=sc.nextInt();
        if(num>0 && num<=500){
            for(int i=2; i<num; i=i+2){
                System.out.print(i+" ");
            }
        }else{
            System.out.print("No results"); 
                                                
        }
    }
}
}