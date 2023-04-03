import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please gives a word to check: ");
        String str=sc.next();
        CheckPalindrome str2=new CheckPalindrome(str);
        System.out.print("Choose method (REV,LOOP): ");
        String method = sc.next();

        boolean bl = false;

        if(method.equals("REV")) bl = str2.isPalindromeRev();
        else if(method.equals("LOOP")) bl = str2.isPalindromeLoop();

        if(bl == true){
            System.out.println(str + " is a Palindrome");
        }else{
            System.out.println(str + " is not a Palindrome");
        }
        sc.close();
    }
}

class CheckPalindrome{
    private String st;
    public CheckPalindrome(String st){
        this.st=st;
    }
    public boolean isPalindromeRev(){
        int n = st.length();
        String Rstr="";
        for(int i=0; i<n; i++){
            Rstr=st.charAt(i) + Rstr;
        }
        return st.equals(Rstr);
    }
    public boolean isPalindromeLoop(){
        int n = st.length();
        int state = 1;
        for(int i=0; i<(n/2); i++){
            if(st.charAt(i) != st.charAt(n-(i+1))){
                state =0;
                break;
            }
        }
        return state==1;
    }
}