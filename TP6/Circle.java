package Ex1;
import java.util.Scanner;
class Point{
    private int x,y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public double distance(Point point, Point anotherPoint){
        double distance=0;
        distance = Math.sqrt(Math.pow(anotherPoint.x - point.x, 2))+Math.pow(anotherPoint.y - point.y, 2);
        return distance;
    }
}

class TestCircle{
    private Point center;
    private Point p;

    public TestCircle(Point center, Point p) {
        this.center = center;
        this.p = p;
    }

    public double length() {
        double length = 0;
        length = 2 * Math.PI * center.distance(center, p);
        return length;
    }

    public double radius() {
        double radius = 0;
        radius = center.distance(center, p);
        return radius;
    }

    public double surface() {
        double surface = 0;
        surface = Math.PI * radius() * length();
        return surface;

    }
}

public class Circle {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input center ");
        System.out.printf("x= ");
        int x1 = sc.nextInt();
        System.out.printf("y= ");
        int y1 = sc.nextInt();

        System.out.println("Input to point ");
        System.out.printf("x= ");
        int x2 = sc.nextInt();
        System.out.printf("y= ");
        int y2 = sc.nextInt();

        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);

        TestCircle c = new TestCircle(p1, p2);

        System.out.printf("Length %.2f\n", c.length());
        System.out.printf("Radius %.2f\n", c.radius());
        System.out.printf("Surface %.2f\n", c.surface());

    }
}
