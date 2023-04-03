import java.util.Scanner;

public class Maxamong8Numbers {
    private int length;
    public Maxamong8Numbers() {
    }
    public int getlength() {
        return length;
    }
    public void setlength(int length) {
        this.length = length;
    }
    private int [] n = new int [length];
    
    public int[] getN() {
        return this.n;
    }
    public void setN(int[] array) {
        this.n = array;
    }
    public Maxamong8Numbers(int Length, int[] array){
        this.n = array;
        this.length = Length;
    }
    public Maxamong8Numbers(int Length){
        this.length = Length;
    }
    public int max(){
        int Max = n[0];
        for(int k = 0; k<n.length; k++){
            if(n[k] > Max){
                Max = n[k];
            }
        }
        return Max;
    }
    public static void main(String[] args){
        int[] array = new int[8];
        try (Scanner h = new Scanner(System.in)) {
            System.out.println("\t\t****** Find Maximum Number of 8 integers ********");
    
            for(int j = 0; j<array.length; j++){
                System.out.print("\t\tInput Number #" +(j+1)+": ");
                array[j]= h.nextInt();
            }
        }
        Maxamong8Numbers m = new Maxamong8Numbers(array.length,array);
        System.out.println("\t\t---->The Maximum number is : " +m.max());
        
    }
    
}
