import java.util.Scanner;

public class MoneyExchannges {

    private double Reil,Dollar,Baht;

    public MoneyExchannges(){
        Dollar = 0;
        Reil = 0;
        Baht = 0;
    }
    public double getReil() {
        return Reil;
    }
    public void setReil(double reil) {
        Reil = reil;
        Dollar = Reil / 4100;
        Baht = Dollar*30;
    }
    public double getDollar() {
        return Dollar;
    }
    public void setDollar(double dollar) {
        Dollar = dollar;
        Reil = Dollar * 4100;
        Baht = Dollar* 30;
    }
    public double getBaht() {
        return Baht;
    }
    public void setBaht(double baht) {
        Baht = baht;
        Dollar = Baht/30;
        Reil = Dollar*4100;
    }
    public static void main(String[] args) {
        try (Scanner h = new Scanner(System.in)) {
            int opt=-1;
            do{
                System.out.println("Welcome to program Money Exchanges!");
                System.out.println("------------------------------------");
                System.out.println("\t 1. Riels to Dollar.");
                System.out.println("""
                        \t 2. Riels to Thai Baht.
                        \t 3. Dollar to Riels.
                        \t 4. Dollar to Thai Baht.
                        \t 5. Thai Baht to Riels.
                        \t 6. Exit.
                        """);
                MoneyExchannges n = new MoneyExchannges();
            
                System.out.print("Choose your Option:");
                int option = h.nextInt();
                if(option==6){
                    System.out.println("\tGoodBye");
                    System.out.println("\n");
                    break;
                }
                
                switch(option){
                    case 1:
                        n.setReil(option);
                        System.out.print("Input Reils Money : ");
                        Double m = h.nextDouble();
                        n.setReil(m);
                        System.out.print("Reil to Dollar: " +n.getDollar());
                        System.out.println("\n");
                    break;
                    case 2:
                        n.setReil(option);
                        System.out.print("Input Reils Money : ");
                        Double a = h.nextDouble();
                        n.setReil(a);
                        System.out.print("Reil to Baht: "+n.getBaht());
                        System.out.println("\n");
                    break;
                    case 3:
                        n.setDollar(option);
                        System.out.print("Input Dollar Money : ");
                        Double b = h.nextDouble();
                        n.setDollar(b);
                        System.out.print("Dollar to Reil: "+n.getReil());
                        System.out.println("\n");
                    break;
                    case 4:
                        n.setDollar(option);
                        System.out.print("Input Dollar Money : ");
                        Double c = h.nextDouble();
                        n.setDollar(c);
                        System.out.print("Dollar to Reil: "+n.getBaht());
                        System.out.println("\n");
                    break;
                    case 5:
                        n.setBaht(option);
                        System.out.print("Input Baht money: ");
                        Double d = h.nextDouble();
                        n.setBaht(d);
                        System.out.print("Baht to Reils: "+n.getReil());
                        System.out.println("\n");
                    break;
                    default:
                        System.out.println("\tExit");
        
                }
             
            }while(opt!=6);
        }

    }

}

