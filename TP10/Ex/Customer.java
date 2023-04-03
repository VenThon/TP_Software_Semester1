package Ex;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Customer {
    public static Scanner sc = new Scanner(System.in);

    private int number;
    private String dateEnter;
    private boolean status;

    

    public void dataInput() {
        while (true) {
            System.out.print("Enter number: ");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                break;
            } else {
                sc.nextLine();
                System.out.println(">>>Invalid input.");
            }
        }


        setDateEnter();
        System.out.println("Date enter : " + dateEnter);
        char opt;
        do {
            System.out.print("Status: (1. Waiting to order, 2. Waiting for food)\n>>>Enter choice: ");
            opt = sc.next().charAt(0);
        } while (opt != '1' && opt != '2');
        if (opt == '1')
            status = false;
        else
            status = true;
    }

    public void setDateEnter() {//current date
        Date D = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        try {
            D=sf.parse(sf.format(D));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateEnter = sf.format(D);
    }

    public void dataOuput() {
        System.out.printf("%-9d %-30s %s", number, dateEnter, status ? "Waiting to order" : "Waiting for serving");
    }

    public int getNumber() {
        return number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDateEnterAsString(){
        return dateEnter;
    }


    public java.sql.Timestamp getDateEnter(){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        java.util.Date d=null;
        try {
            d= sdf.parse(dateEnter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(d));
        return new java.sql.Timestamp(d.getTime());
    }

}
