import java.util.ArrayList;
import java.util.Scanner;
public class SMSDecryption {
    SMSList list = new SMSList();
    SMS sms = new SMS();
    ArrayList<SMS> arr = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int n=0;
        SMSList list = new SMSList();
        int op;
        do {
            System.out.print("""

                    ---------------------------------Menu--------------------------------------
                    1. List all SMSes
                    2. View SMS Detail (decrypt content using password)
                    3. View readable SMSes (all SMS that can be decrypted using given password)
                    4. Remove SMSes by index
                    5. Quit
                Please choose the option= """);
            op = Integer.parseInt(input.next());
            
            switch (op) {
                case 1: {
                    list.decryptedContent(n);
                    list.listSms();
                    break;
                }
                case 2: {
                    String res = list.decryptedContent(n+1);
                    if (res != "false") {
                        list.viewSMSDetail();
                        n = n+2;
                    }
                    break;
                }
                case 3: {
                    if (n != 0) {
                        list.viewSMSDetail();
                        list.readableSms();
                    } else {
                        System.out.println("First, we need to decrypted content in option 2 !!");
                    }
                    break;
                }
                case 4: {
                    list.removeSms();
                    break;
                }
                case 5: {
                    System.out.println("Thanks for using our program !!");
                    System.out.print("\n-----------------------------------------------------------------------");
                    break;
                }
                default: System.out.println("ERROR CHOOSING OPTION !!!");
            }
                
        } while (op != 5);
        input.close();
    }
}
