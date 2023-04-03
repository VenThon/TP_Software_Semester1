import java.util.Scanner;
public class ReversingNumber{
    
    private int num;
    public ReversingNumber(int d){
        this.num = d;
    }
    public int get(){
        return this.num;
    }
    public void set(int data){
        this.num=data;

    }
    public int ReversNumber(){
        int reverse = 0;
        while(this.num != 0)   {  
            int remainder = this.num % 10;  
            reverse = reverse * 10 + remainder;  
            this.num = this.num/10;  
        } 
        return reverse;
    }
    public boolean isValid(){
        int count = 0;
        while(this.num != 0){
            this.num = this.num/10;
            count += 1;
        }
        return count == 4 ? true:false;
    }
    public static void main(String[] args){
        try (Scanner h = new Scanner(System.in)) {
            System.out.println("Program for reversing a 4 digits number.");
            System.out.print("Please input 4 digits number: ");
            int num = h.nextInt();
            ReversingNumber n = new ReversingNumber(num);

            if(num>=1000){
                System.out.println("After Revers: " +n.ReversNumber());
            }else{
                System.out.println("invalid number, please input only 4 digits number.");
            }
        }
    }
}