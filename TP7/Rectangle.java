import java.util.Scanner;
class instance{
    private int width;
    private int height;
    public instance(int width,int height){
        this.width =  width;
        this.height = height;
    }public int calculatePerimeter(){
        
        return (width+height)*2;
    }
     public int calculateSurface(){
        return width * height;   
    } 

}
public class Rectangle{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter value of widh: ");
        int width=sc.nextInt();
        System.out.print("Enter value of height: ");
        int height=sc.nextInt();
        instance c = new instance(width, height);
        System.out.println("Perimeter is: "+c.calculatePerimeter());
        System.out.println("Surface is: "+c.calculateSurface());
    
    }
}