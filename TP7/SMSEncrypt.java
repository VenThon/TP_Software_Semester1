import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
class SMS {
    String sub;
    String rpn;
    String con;
    String fpn;
    StatusSms status = StatusSms._new;
    TypeSms type = TypeSms.text;

    public String getsubject() {
        return this.sub;
    }
    
    public void setsubject(String subject) {
        this.sub = subject;
    }
    
    public String getReceiver_number() {
        return this.rpn;
    }
    
    public void setReceiver_number(String Receiver_number) {
        this.rpn = Receiver_number;
    }
    
    public String getcontent() {
        return this.con;
    }

    public void setcontent(String content) {
        this.con = content;
    }
   
    public String getFrom_phone_number() {
        return this.fpn;
    }

    public void setFrom_phone_number(String From_phone_number) {
        this.fpn = From_phone_number;
    }

    public SMS() {
        this("", "", "", "");
    }

    public SMS(String subject, String From_phone_number, String Receiver_number, String content) {
        this.sub = subject;
        this.fpn = From_phone_number;
        this.rpn = Receiver_number;
        this.con = content;
    }
}

enum TypeSms {
    text, MMS
}

enum StatusSms {
    _new, _read
}
public class SMSEncrypt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    ArrayList<SMS> arr = new ArrayList<>();
    SMS sms = new SMS();
    static  max_Character = 55; 
    int verify = 1;

    public void send_sms() throws Exception {
        String con,sub,sender,receiver;
        SMS sms = new SMS();
        System.out.print("Enter Subject= ");
        sub = input.nextLine();
        sms.setsubject(sub);
        System.out.print("From phone number= ");
        sender = input.nextLine();
        sms.setFrom_phone_number(sender);
        System.out.print("To phone number= ");
        receiver = input.nextLine();
        sms.setReceiver_number(receiver);
        System.out.print("Enter content(Under 55 words): ");
        con = input.nextLine();
        String encryp_content = encryptedContent(con,sub,sender,receiver);
        sms.setcontent(encryp_content);
        sms.type = TypeSms.text;
        sms.status = StatusSms._new;

        arr.add(sms);
    }
    
    public String encryptedContent(String con, String sub, String sender, String receiver) throws Exception {

        FileOutputStream myWriter = new FileOutputStream("satra.txt", true);
        con = con + ";" + sub + ";" + sender + ";" + receiver + ":\n";
        myWriter.write(con.getBytes());
        myWriter.close();

        FileInputStream inFile = new FileInputStream("satra.txt");
        FileOutputStream outFile = new FileOutputStream("satra.des");

        String password = "1234";

        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory
                .getInstance("PBEWithMD5AndTripleDES");
        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);

        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
        outFile.write(salt);

        byte[] input = new byte[64];
        int bytesRead;
        while ((bytesRead = inFile.read(input)) != -1) {
            byte[] output = cipher.update(input, 0, bytesRead);
            if (output != null)
                outFile.write(output);
        }

        byte[] output = cipher.doFinal();
        if (output != null)
            outFile.write(output);

        FileReader fileReader = new FileReader("satra.des");

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line, conline = "";
        while ((line = bufferedReader.readLine()) != null) {
            conline = conline + line;
        }
        bufferedReader.close();
        inFile.close();
        outFile.flush();
        outFile.close();
        return conline;
    }

    public String decryptedContent(int pass) throws Exception {

        String password = "1234";
        String res = "";
        if (pass == 1) {
            do {
                System.out.print("Enter password for using decryption("+(4-pass)+" try): ");
                password = input.next();
                if (password.compareTo("1234") == 0) {
                    System.out.println("Password corrected!");
                    break;
                } else {
                    System.out.println("ERROR password!");
                }
                pass++;
            } while (pass != 4);
        }
        if (password.compareTo("1234") == 0) {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            FileInputStream fis = new FileInputStream("satra.des");
            byte[] salt = new byte[8];
            fis.read(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);

            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            FileOutputStream fos = new FileOutputStream("satra_decrypted.txt");
            byte[] in = new byte[64];
            int read;
            while ((read = fis.read(in)) != -1) {
                byte[] output = cipher.update(in, 0, read);
                if (output != null)
                    fos.write(output);
            }

            byte[] output = cipher.doFinal();
            if (output != null)
                fos.write(output);
            fis.close();
            fos.flush();
            fos.close();
            if (verify == 1) {
                readSms();
            }
            verify = 0;
        } else {
            System.out.println("ERROR password!! try again!!");
            res = "false";
        }
        return res;
    }
   
    public void readSms() throws Exception {
        
        int num = 100;
        String[] subject = new String[num];
        String[] sender = new String[num];
        String[] receiver = new String[num];
        String[] content = new String[num];

        FileReader fileReader = new FileReader("satra_decrypted.txt");

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line, str = "";
        while ((line = bufferedReader.readLine()) != null) {
            str = str + line;
        }
        
        bufferedReader.close();
        

        String[] student_data = str.split(":");

        int c = 0;
        for (String a : student_data) {
            SMS sms = new SMS();
            String[] student = a.split(";");

            content[c] = student[0];
            subject[c] = student[1];
            sender[c] = student[2];
            receiver[c] = student[3];

            sms.setsubject(subject[c]);
            sms.setFrom_phone_number(sender[c]);
            sms.setcontent(content[c]);
            sms.setReceiver_number(receiver[c]);
            sms.type = TypeSms.text;
            sms.status = StatusSms._new;
            arr.add(sms);

            c++;
        }

    }
    
    
    public void readableSms() {
        System.out.print("Input index of Sms[0-" + (arr.size() - 1) + "]: ");
        int index = Integer.parseInt(input.next());

        if (index >= 0 && index < arr.size()) {
            System.out.println(
                    "\nSubject: " + arr.get(index).getsubject() + "\tFrom: " + arr.get(index).getFrom_phone_number());
            System.out.println("To: " + arr.get(index).getReceiver_number());
            System.out.println("\nContent:");
            System.out.println("\t" + arr.get(index).getcontent());
            arr.get(index).status = StatusSms._read;
        } else {
            System.out.println("Index out of bound!");
        }
    }

    public void viewSMSDetail() {
        if(verify == 1) {
            System.out.println("""
                        
            
                        -------------------------------------------------------------------------------------------------------------------
                        |        Subject        |  Sender Phone Number | Receiver Phone Number |  Type  |   Content(encrypted)   | Status |
                        -------------------------------------------------------------------------------------------------------------------""");
        }else {
            System.out.println("""
            
                        -------------------------------------------------------------------------------------------------------------------
                        |        Subject        |  Sender Phone Number | Receiver Phone Number |  Type  |        Content        | Status |
                        -------------------------------------------------------------------------------------------------------------------""");
        }
        for (SMS sms : arr) {
            System.out.printf("| %-20s  | %-20s | %-21s | %-6s | %-17.17s...   | %-7s |\n", sms.getsubject(),
                    sms.getFrom_phone_number(),
                    sms.getReceiver_number(), sms.type,
                    sms.getcontent(),sms.status);
        }
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------");
    }
    
    public void listSms() {
        System.out.println("""

                        ---------------------------------------------------------------------------------
                        |        Subject        |  Sender Phone Number | Receiver Phone Number |  Type  |
                        ---------------------------------------------------------------------------------""");
        for (SMS sms : arr) {
            System.out.printf("| %-20s  | %-20s | %-21s | %-6s |\n", sms.getsubject(),
                    sms.getFrom_phone_number(),
                    sms.getReceiver_number(), sms.type);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    public void removeSms() {
        int removeSms;
        System.out.print("\nInput index[0-" + (arr.size() - 1) + "] to remove: ");
        removeSms = Integer.parseInt(input.next());

        if (removeSms < arr.size() && removeSms >= 0) {
            arr.remove(removeSms);
            System.out.println("SUCCESSFULLY REMOVED!");
        } else {
            System.out.println("ERROR: INDEX OUT OF RANK!");
        }
    }
    

    public static void main(String[] args) throws Exception {
        int op;
        Scanner input = new Scanner(System.in);
        SMSList manageSms = new SMSList();

        do{
            System.out.print("""
            
                -----------------------------Menu-------------------------------------
                1. Send new SMS with Encrypted content using password method
                2. View SMS detail
                3. List SMSes
                4. Remove SMSes by index
                5. Quit
            Please choose an option = """);
            op = Integer.parseInt(input.next());
            switch (op) {
                case 1: {
                    manageSms.send_sms();
                    break;
                }
                case 2: {
                    manageSms.viewSMSDetail();
                    break;
                }
                case 3: {
                    manageSms.listSms();
                    break;
                }
                case 4: {
                    manageSms.viewSMSDetail();
                    manageSms.removeSms();
                    break;
                }
                case 5: {
                	System.out.print("Thanks for using our program !!!"); 
                	System.out.println("\n-----------------------------------------------------------------------");
                	break;
                }
                default: System.out.println("Enter not found !!!");
            }
        } while (op != 5);

        input.close();
    } 
    }
}
