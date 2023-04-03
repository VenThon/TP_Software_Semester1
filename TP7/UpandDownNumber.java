// package TP7;
import java.util.Scanner;
public class UpandDownNumber {
    public int n;
    
   public UpandDownNumber(int n) {
        this.n = n;
    }

    public String up_down_number(){
        String str=" ";

        int o=n;
        if(o%2==0){
            o=o-1;
        }
        for(int i=o;i>=1;i=i-2){
            str=str+"  "+String.valueOf(i);
        }
        for(int i=2;i<=n;i=i+2){
            str=str+"  "+String.valueOf(i);
        }
        return str;
    }

    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int p=0;
    while(true){
        if(p==0){
            System.out.print("Input positive number: ");
            p=sc.nextInt();
        }else if(p<0){
            System.out.print("Input only positive number: ");
            p=sc.nextInt();
        }else break;
    }


        UpandDownNumber ex1=new UpandDownNumber(p);
        System.out.println(ex1.up_down_number());
  }
}
