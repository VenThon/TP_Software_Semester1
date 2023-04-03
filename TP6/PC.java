import java.util.*;
public class PC {
    String brand;
    String status;
    ArrayList<PC> Pcs = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public PC(String brand, String status) {
        this.brand = brand;
        this.status = status;
    }

    public String getName() {
        return brand;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PC(){
        for (int i=0; i<20; i++){
            if(i>=1 && i<=5 || i>10 && i<15){
                PC pc = new PC("Computer"+i, "good");
                Pcs.add(pc);
            }else{
                PC pc = new PC("Computer"+i, "demage");
                Pcs.add(pc);
            }
        }
    }

    public void markDamageToPc() {
        int checkPc = 0;
        listGoodPcs();
        System.out.print("Search pc's name:");
        String pc = sc.nextLine();
        for (PC pcs : Pcs) {
            if (pc.equals(pcs.getName())) {
                System.out.print("Change status to damage (damage):");
                pc = sc.nextLine();
                checkPc = 1;
                if ("damage".equals(pc)) {
                    pcs.setStatus(pc);
                    System.out.println("successfull to change status");
                    checkPc = 2;
                    break;
                }
            }
        }
        if (checkPc == 0) {
            System.out.println("There is no " + pc + " in List");
        }
        if (checkPc != 2) {
            System.out.println("Please input the word damage");
        }
    }

    public void markGoodToPc() {
        int checkPc = 0;
        listDamagePcs();
        System.out.print("Search pc's name:");
        String pc = sc.nextLine();
        for (PC pcs : Pcs) {
            if (pc.equals(pcs.getName())) {
                System.out.print("Change status to good(good):");
                pc = sc.nextLine();
                checkPc = 1;
                if ("good".equals(pc)) {
                    pcs.setStatus(pc);
                    System.out.println("success to change status");
                    checkPc = 2;
                    break;
                }
            }
        }
        if (checkPc == 0) {
            System.out.println("There is no " + pc + " in List");
        }
        if (checkPc != 2) {
            System.out.println("Please input the word damage");
        }
    }

    public void listAllPC() {
        int i = 0;
        System.out.println("\nrid\tPC's name\tstatus");
        for (PC pc : Pcs) {
            System.out.println((i + 1) + "\t" + pc.brand + "\t" + pc.status);
            i += 1;
        }
    }

    public void listGoodPcs() {
        int i = 0;
        System.out.println("rid\tPcs'Name\tStatus");
        for (PC pcs : Pcs) {
            if ("good".equals(pcs.getStatus())) {
                System.out.println((i + 1) + "\t" + pcs.getName() + "\t" + pcs.getStatus());
                i += 1;
            }
        }
    }

    public void listDamagePcs() {
        int i = 0;
        System.out.println("rid\tPcs'Name\tStatus");
        for (PC pcs : Pcs) {
            if ("damage".equals(pcs.getStatus())) {
                System.out.println((i + 1) + "\t" + pcs.getName() + "\t" + pcs.getStatus());
                i += 1;
            }
        }
    }

    public void readMenu() {
        System.out.println("1.List all PCs\n2.List all damages PCs\n3.List all good PCs\n"
                + "4.Mark a PC as damaged\n5.Mark a PC as not damaged\n6.Quit\n");
        System.out.print("Enter your choice:");
    }

    public String getStatus() {
        return status;
    }

}

class LabPC{
    public static void main(String[] args) {
        PC pcs = new PC();
        Scanner sc = new Scanner(System.in);
        int choice; 
        while (true) {
            pcs.readMenu();
            choice = sc.nextInt();
            if (choice == 1)
                pcs.listAllPC();
            else if (choice == 2)
                pcs.listDamagePcs();
            else if (choice == 3)
                pcs.listGoodPcs();
            else if (choice == 4)
                pcs.markDamageToPc();
            else if (choice == 5)
                pcs.markGoodToPc();
            else if (choice == 6) {
                System.out.println("Bye! Bye!");
                break;
            }
        }
    }
}