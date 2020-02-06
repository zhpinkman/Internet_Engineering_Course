package McFayyaz;
import java.lang.Math;

public class Location {
    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void print(){
        System.out.println("X: " + x + " | Y: " + y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDistanceFromLocation(Location location) {
        return Math.pow(x - location.x, 2) + Math.pow(y - location.y, 2);
    }
}
