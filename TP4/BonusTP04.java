import java.util.Scanner;
public class BonusTP04 {
    public static void main(String[] args) {
        try (Scanner h = new Scanner(System.in)) {
            int opt = -1;
            do{
                System.out.println("============== MENU ==============");
                System.out.println("\t 1.Prime Number");
                System.out.println("\t 2.Lucky Number");
                System.out.println("\t 3.Revering Number");
                System.out.println("\t 4.Money Exchange");
                System.out.println("\t 5.Max Among 8 Numbers");
                System.out.println("\t 6.Shipping");
                System.out.println("\t 7.Leap Year");
                System.out.println("\t 0.Exit");
                System.out.println("==================================");
                System.out.print("Please choose one Option: ");
                opt = h.nextInt();
                if(opt == 0){
                    System.out.println("BYe BYe!!");
                    System.out.println("\n");
                    break;
                }
                switch(opt){
                    case 1:
                            PrimeNumber.main(args);
                            System.out.println("\n");
                    break;
                    case 2:
                            LuckyNumber.main(args);
                            System.out.println("\n");
                    break;
                    case 3:
                            ReversingNumber.main(args);
                            System.out.println("\n");
                    break;
                    case 4:
                            MoneyExchannges.main(args);
                            System.out.println("\n");
                    break;
                    case 5:
                            Maxamong8Numbers.main(args);
                            System.out.println("\n");
                    break;
                    case 6:
                            Shipping.main(args);
                            System.out.println("\n");
                    break;
                    case 7:
                            LeapYear.main(args);
                            System.out.println("\n");
                            
                }
                   
                }while(opt!=0);
        }
        }
    }

