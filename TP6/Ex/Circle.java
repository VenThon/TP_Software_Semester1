package Ex;

public class Circle {
    private Point center, p; // p on the border of circle

    public Circle(Point center, Point p) {
        this.setCenter(center);;
        this.setP(p);
    }

    public double radius () {
        return center.distance(p); 
    }

    public double perimeter() {
        // P = 2*PI*R
        return 2 * Math.PI * radius();
    }

    public double surface() {
        // S = PI*R*R
        return Math.PI *radius()*radius();

    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        if(center != null) {
            this.center = center;

        } 
        else {
            System.err.println("ERROR: center could not be null ");
        }
        this.center = center;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

}


