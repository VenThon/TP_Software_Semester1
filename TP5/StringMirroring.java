import java.util.Scanner;
public class StringMirroring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the woed: ");
        String paragrap = sc.next();
        StringMirror paragrap1 = new StringMirror(paragrap);
        System.out.println(paragrap1.Palindrome());
        
    }
}

class StringMirror{
    private String str;

    public StringMirror(String str){
        this.str=str;
    }

    public String Palindrome(){
        int n=str.length();
        String strev="";
        for(int i=0; i<n; i++){
           strev=str.charAt(i) + strev;
        }
        return str  + strev;
    }
}