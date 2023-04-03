package Ex;

public class CircleTest {
    public static void main(String[] args) {
        Point c = new Point();
        Point p = new Point(5, 0);
        Point p2 = new Point(15,10);

        Circle circle = new Circle(c, p);
        Circle circle2 = new Circle(c, p2);
        
        c.setX(5);

        System.out.println("Radius: "+circle.radius());
        System.out.println("Perimeter: "+circle.perimeter());
        System.out.println("Surface: "+circle.surface());
        System.out.println("Radius2: "+circle2.radius());
        System.out.println("Perimeter2: "+circle2.perimeter());
        System.out.println("Surface:  "+circle2.surface());
    }
}

