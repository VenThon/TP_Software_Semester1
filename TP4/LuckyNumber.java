import java.util.Scanner;
public class LuckyNumber {
    private int k ;
    public LuckyNumber (int d) {
       k = d;
    }
    boolean islucky (){
        int Sumfirst = 0, Sumlast = 0;
        for(int i=0; k!=0; i++){
           if(i<3){
                Sumlast = Sumlast+(k%10);
                k = k/10;
           }else{
                Sumfirst = Sumfirst+(k%10);
                k = k/10;
           }
        }if(Sumfirst == Sumlast){
            return true;
        }
        return false;
    } 
    public void setvalue(int data){
        this.k = data;
    }
    public boolean isValid(){
        int count = 0;
        while(this.k != 0){
            this.k = this.k/10;
            count += 1;
        }
        return count == 6 ?true:false;
    }
    public static void main(String[] args){
        try (Scanner h = new Scanner(System.in)) {
            System.out.println("Program for testing for lucky number.");
            System.out.print("Please input 6 digits number: ");
            int num = h.nextInt();
            LuckyNumber k = new LuckyNumber(num);
            

            while (!k.isValid()){
                System.out.print("Invalid input number, please only input 6 digit number: ");
                num = h.nextInt();
                k.setvalue(num);
            }
            if(k.islucky()){
                System.out.println(num +" : is lucky number");
            }else{
                System.out.println(num + ": is not a lucky number");
            }
        }
    }
}
 