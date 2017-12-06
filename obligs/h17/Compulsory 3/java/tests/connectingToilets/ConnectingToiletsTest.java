package connectingToilets;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by knutandersstokke on 28 28.10.2017.
 */
public class ConnectingToiletsTest {

    @Test
    public void testWithOneToilet() {
        Set<Toilet> toiletSet = new HashSet<>();
        toiletSet.add(new Toilet("1", 1, 1));
        Set<Edge> result = ConnectingToilets.connectToilets(toiletSet);
        assertEquals("Should not return any connections", 0, result.size());
        assertEquals("Length should be 0", 0.0, resultLength(result));
    }

    @Test
    public void testWithTwoToilets() {
        Set<Toilet> toiletSet = Stream.of(
                new Toilet("1", 1, 1),
                new Toilet("2", 1, 2)
        ).collect(Collectors.toSet());
        Set<Edge> result = ConnectingToilets.connectToilets(toiletSet);
        assertEquals("Should have 1 connection", 1, result.size());
        assertEquals("Length should be 1", 1.0, resultLength(result));
    }

    @Test
    public void testWithFiveToiletsInStarPosition() {
        Set<Toilet> toiletSet = Stream.of(
                new Toilet("1", 1, 1),
                new Toilet("2", 1, 2),
                new Toilet("3", 0, 1),
                new Toilet("4", 1, 0),
                new Toilet("5", 2, 1)
        ).collect(Collectors.toSet());
        Set<Edge> result = ConnectingToilets.connectToilets(toiletSet);
        assertEquals("Should have n-1 connection", toiletSet.size() - 1, result.size());
        assertEquals("Length in this case should be 4", 4.0, resultLength(result));
    }

    @Test
    public void testWithFiveToiletsOnALine() {
        Set<Toilet> toiletSet = Stream.of(
                new Toilet("1", 0, 0),
                new Toilet("2", 1, 0),
                new Toilet("3", 3, 0),
                new Toilet("4", 6, 0),
                new Toilet("5", 10, 0)
        ).collect(Collectors.toSet());
        Set<Edge> result = ConnectingToilets.connectToilets(toiletSet);
        assertEquals("Should have n-1 connection", toiletSet.size() - 1, result.size());
        assertEquals("Length in this case should be 10", 10.0, resultLength(result));

    }

    @Test
    public void testForBergen() {
        Set<Toilet> toiletSet = ConnectingToilets.readToiletsFromFile("connectingToilets");
        Set<Edge> result = ConnectingToilets.connectToilets(toiletSet);
        assertEquals("Minimum length for Bergen should be", 1069.628417717744, resultLength(result), 0.1);
    }

    private double resultLength(Set<Edge> result) {
        return result.stream()
                .mapToDouble(Edge::getLength)
                .sum();
    }

}