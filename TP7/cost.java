import java.util.Scanner;
public class cost {
    public static double Discount(double price){
        if(price>=50 && price<100){
            return 0.01;
        }else if(price>=100 && price<200){
            return 0.15;
        }else if(price>=200 && price<350){
            return 0.2;
        }else if(price>=350 && price<500){
            return 0.25;
        }else if(price>=500){
            return 0.3;
        }
        return 0;
    }
    public static double Discount(double price,int age){
        if(age>60){
            return Discount(price)*2;
        }
        return Discount(price);
    }

    public static double getDouble(String text,String Err){
        double data;
        Scanner Getinput=new Scanner(System.in);
        System.out.print(text);
        while(!Getinput.hasNextDouble()){
            System.out.print(text);
            Getinput.next();
        }
        data=Getinput.nextDouble();
        while(data<0){
            System.out.print(Err);
            System.out.print(text);
            while(!Getinput.hasNextInt()){
                System.out.print(text);
                Getinput.next();
            }
            data=Getinput.nextDouble();
        }
        return data;
    }
    public static void main(String[] arg){

        double Price= getDouble("Inpt total buying Cost:", " Cost must be positive\n");
        int age= (int)getDouble("Inpt age:", "Age must be positive\n");
        System.out.println("\n\n");
        System.out.println("\t\t Total cost: "+ Price+"$");
        System.out.println("\t\t  The discount is:"+(Price*Discount(Price,age))+"$");
        System.out.println("\t\t  Price to pay is:"+(Price-(Price*Discount(Price,age)))+"$");
        System.out.println("-----------------------------------------------");
        System.out.println("\t\t  Price to pay is:"+(Price-(Price*Discount(Price,age)))+"$");
    }
}
