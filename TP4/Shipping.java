
import java.util.Scanner;

public class Shipping {
    private double max_capable = 5000;

    public double getMax_capable() {
        return max_capable;
    }
    private double DistanceA_B, DistanceB_C, weight;

    public double getDistanceA_B() {
        return this.DistanceA_B;
    }
    public void setDistanceA_B(double distanceA_B) {
        this.DistanceA_B = distanceA_B;
    }
    public double getDistanceB_C() {
        return this.DistanceB_C;
    }
    public void setDistanceB_C(double distanceB_C) {
        this.DistanceB_C = distanceB_C;
    }
    public Shipping( double DistanceA_B, double DistanceB_C, double weight){
        this.DistanceA_B = DistanceA_B;
        this.DistanceB_C = DistanceB_C;
        this.weight = weight;
    }
    public Shipping(){

    }
    public boolean cannot_load(){
        if(weight>30000){
            return false;
        }
        return true;
    }
    public double NumOfLiters(){
        if(weight<=5000){
            return 10;
        }else if (weight<=10000){
            return 20;
        }else if (weight<=20000){
            return 25;
        }else if(weight<=30000){
            return 35;
        }
        return 10;
    }
    public double LiterUsed(double distance){
        if(!cannot_load())
        return 0;
        return distance*NumOfLiters();
    }
    public static void main(String[] args) {
        try (Scanner h = new Scanner(System.in)) {
            System.out.println("\t======== Calculate the minimum number of liters =======");
            Shipping l = new Shipping();
            System.out.print("Input distance from A to B : ");
            l.setDistanceA_B(h.nextDouble());
            System.out.print("Input distance from B to C : ");
            l.setDistanceB_C(h.nextDouble());
            System.out.print("Input Weigth: ");
            l.weight = h.nextDouble();
            if(!l.cannot_load()){
                System.out.println("Ohh Sorry,the weigth too heavy cannot be loaded");
            }else{
                double shipleftA_B = l.getMax_capable() - l.LiterUsed(l.getDistanceA_B());
                if(shipleftA_B<0){
                    System.out.println("cannot go, because your ship is out of petrol.");
                }else{
                    double shipgoB_C = l.LiterUsed(l.getDistanceB_C());
                    if(shipleftA_B >= shipgoB_C){
                        System.out.println("Now You have enough Petrol : " +shipleftA_B+ ",so you can go to point C");
                    }else{
                        System.out.println("You need to refill at least " +(shipgoB_C - shipleftA_B)+" if you want to go reach point C.");
                    }
                }
            }
        }
    }

}
