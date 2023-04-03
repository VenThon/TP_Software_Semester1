import java.util.Scanner;
public class Costcalculation {
    Scanner sc = new Scanner(System.in);
    float price;
    public Costcalculation(){

    }
    public void inputCost(){
        while (true) {
            System.out.print("Input cost: ");
            price = sc.nextFloat();
            if(price>=0){
                break;
            }else{
                System.out.println("Cost should be bigger or equal than 0");
            }
        }
    }
    public float calculateCost(){
        if(price>=500)return price-(price*25/100);
        else if(price>=200)return price-(price*12/100);
        else if(price>=100)return price-(price*7/100);
        else if(price>=50)return price-(price*3/100);
        else if(price>=10) return price-2;
        return price;
    }
    public void displayResult(){
        System.out.println("amount of cost: "+calculateCost());
    }
    public static void main(String[] args) {
        Costcalculation product = new Costcalculation();
        product.inputCost();
        product.displayResult();
    } 
}
