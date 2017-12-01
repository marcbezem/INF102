package connectingToilets;

import java.util.Set;

/**
 * Created by knutandersstokke on 16 16.10.2017.
 */
public class ToiletMap {

    private Set<Toilet> toilets;
    private Set<Edge> connections;

    public ToiletMap(Set<Toilet> toilets, Set<Edge> connections) {
        this.toilets = toilets;
        this.connections = connections;
    }

    public Set<Toilet> getToilets() {
        return toilets;
    }

    public Set<Edge> getConnections() {
        return connections;
    }
}
