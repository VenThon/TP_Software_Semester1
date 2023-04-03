package TP9.Point3D;
import java.util.Scanner;
public class PointThreeD {
    private int x,y,z;
    private PointThreeD(){}
    public static PointThreeD inputData(){
        Scanner sc = new Scanner(System.in);
        PointThreeD p = new PointThreeD();
        System.out.print("Input x:");
        try {
            p.setX(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Change to Integer");
        }
        System.out.print("Input y:");
        try {
            p.setY(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Change to Integer");
        }

        System.out.print("Input z:");
        try {
            p.setZ(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Change to Integer");
        }
        return p;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x){
        FieldUtil.checkNullOrEmptyOrBlank(String.valueOf(x), "x");
        this.x =x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        FieldUtil.checkNullOrEmptyOrBlank(String.valueOf(z),"y");
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        FieldUtil.checkNullOrEmptyOrBlank(String.valueOf(z), "z");
        this.z = z;
    }
    @Override
    public String toString(){
        return "{x="+x+",y="+y+",z="+z+"}";
    }
}
class FieldUtil {
    /**
     * Check if a field is null, empty or blank.
     * @param field String field to check
     * @param label Prefix for error message.
     * @throws NullPointerException in case field is null.
     * @throws IllegalArgumentException in case field is empty or blank.
     */
    public static void checkNullOrEmptyOrBlank(String field, String label){
        if(field == null ){
            throw new NullPointerException(label+" should not be null.");
        }
        if(field.isEmpty() || field.isBlank())
            throw new IllegalArgumentException(label+" should not be empty or blank.");
    }
}
