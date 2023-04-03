//  package TP7;

import java.util.Scanner;
class discount{
    private double cost;
    private double discount;
    private double total_price;

    public discount(double cost) {
        this.cost = cost;
    }

    public void discount_price(double dis_price) {
        discount = (cost * dis_price / 100);
        total_price = cost - discount;
        System.out.println("\n\n     Total cost: " + cost + " $");
        System.out.println("       Discount: " + dis_price + " %");
        System.out.println(" Total discount: " + discount + " $");
        System.out.println("--------------------------");

        System.out.println("Total payment: " + total_price + " $");
    }

}

public class ex2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input total buying price: ");
        double cost = sc.nextDouble();

        discount dis = new discount(cost);
        if (cost >= 50) {
            System.out.print("Input age: ");
            int age = sc.nextInt();

            if (cost >= 50 && cost < 100) {
                if (age <= 60) {
                    dis.discount_price(10);
                } else if (age > 60) {
                    dis.discount_price(20);
                }
            } else if (cost >= 100 && cost < 200) {
                if (age <= 60) {
                    dis.discount_price(15);
                } else if (age > 60) {
                    dis.discount_price(25);
                }
            } else if (cost >= 200 && cost < 350) {
                if (age <= 60) {
                    dis.discount_price(20);
                } else if (age > 60) {
                    dis.discount_price(30);
                }
            } else if (cost >= 350 && cost < 500) {
                if (age <= 60) {
                    dis.discount_price(25);
                } else if (age > 60) {
                    dis.discount_price(35);
                }
            } else if (cost >= 500) {
                if (age <= 60) {
                    dis.discount_price(30);
                } else if (age > 60) {
                    dis.discount_price(40);
                }
            }
        } else if (cost > 0 && cost < 50) {
            System.out.println("\n\n     Total cost: " + cost + " $");
            System.out.println(" Total discount: 0.00 $");
            System.out.println("--------------------------");
            System.out.println("Total payment: " + cost + " $");
        } else {
            System.out.println("Cost must be positive.\n");
            ex2.main(args);
        }
    }
}

