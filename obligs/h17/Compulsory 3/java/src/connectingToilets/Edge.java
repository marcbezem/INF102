package connectingToilets;

/**
 * Represents an edge or a network cable between two scientific employees
 */
public class Edge implements Comparable<Edge> {
    private Toilet toiletA;
    private Toilet toiletB;
    private double length;

    public Edge(Toilet toiletA, Toilet toiletB, double length) {
        this.toiletA = toiletA;
        this.toiletB = toiletB;
        this.length = length;
    }

    public Toilet getToiletA() {
        return toiletA;
    }

    public Toilet getToiletB() {
        return toiletB;
    }

    public double getLength() {
        return length;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(length, o.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (Double.compare(edge.length, length) != 0) return false;
        if (toiletA != null ? !toiletA.equals(edge.toiletA) : edge.toiletA != null) return false;
        return toiletB != null ? toiletB.equals(edge.toiletB) : edge.toiletB == null;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "toiletA=" + toiletA +
                ", toiletB=" + toiletB +
                ", length=" + length +
                '}';
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = toiletA != null ? toiletA.hashCode() : 0;
        result = 31 * result + (toiletB != null ? toiletB.hashCode() : 0);
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
