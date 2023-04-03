import java.util.Scanner;
public class ex3 {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      System.out.println("Program for calculating cost of a call.");
      System.out.print("Enter start hours: ");
      int hs=sc.nextInt();
      System.out.print("Enter start minutes: ");
      int ms=sc.nextInt();
      System.out.print("Enter start seconds: ");
      int ss=sc.nextInt();

      System.out.print("Enter end hours: ");
      int he=sc.nextInt();
      System.out.print("Enter end minutes: ");
      int me=sc.nextInt();
      System.out.print("Enter end seconds: ");
      int se=sc.nextInt();

      int h=he-hs;
      int m=me-ms;
      int s=se-ss;

      int total=(h*3600) + (m*60) + s;
      int Hnew=total/60;
      int Mnew=(total%60)/60;
      int Snew=(total%60)%60;
        // Hnew=Hnew/60;
      int new_min=total/60;
      float cost=(float)(total*(0.05/60));
      System.out.println("Total call duration: "+ Hnew + "h " +Mnew+"mn "+Snew+"s");
      System.out.printf("Total cost of this call: %.4f$",cost);
    }
}
