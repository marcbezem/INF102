package connectingToilets;

/**
 * Created by knutandersstokke on 16 16.10.2017.
 */
public class Toilet {

    private String name;
    private double x;
    private double y;

    public Toilet(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Toilet{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public double getY() {
        return y;
    }
}
