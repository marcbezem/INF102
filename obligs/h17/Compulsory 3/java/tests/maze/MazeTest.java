package maze;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by knutandersstokke on 28 28.10.2017.
 */
public class MazeTest {

    @Test
    public void testSimplestMaze() {
        Maze maze = new Maze("maze/simplest_maze.txt");
        Point goal = maze.getGoal();
        List<Point> result = Maze.solve(maze);
        assertEquals("Solution should only containt one point", 1, result.size());
        assertEquals("The point from the solution should be the goal", goal, result.get(0));
    }

    @Test
    public void testExampleMaze() {
        Maze maze = new Maze("maze/example_maze.txt");
        List<Point> expectedSolution = Stream.of(
                new Point(1, 2),
                new Point(1, 1),
                new Point(1, 0),
                new Point(0, 0)
        ).collect(Collectors.toList());
        List<Point> result = Maze.solve(maze);
        assertEquals("Solution should be equal to expected", expectedSolution, result);
    }

    @Test
    public void testMazeExact() {
        Maze maze = new Maze("maze/maze.txt");
        List<Point> result = Maze.solve(maze);
        assertEquals("Solution should containt 240 points", 240, result.size());
    }

    @Test
    public void testMazeValidPath() {
        Maze maze = new Maze("maze/maze.txt");
        List<Point> result = Maze.solve(maze);
        assertTrue("Points should be next to each other", pointsAreNextToEachOther(result));
    }

    private boolean pointsAreNextToEachOther(List<Point> points) {
        return IntStream.range(1, points.size())
                .mapToObj(i -> new Pair(points.get(i-1), points.get(i)))
                .allMatch(MazeTest::distLessThanOrEq1);
    }

    private static boolean distLessThanOrEq1(Pair p) {
        double dx = p.b.x - p.a.x;
        double dy = p.b.y - p.a.y;
        return Math.sqrt(dx * dx + dy * dy) <= 1;
    }

    private static class Pair {
        Point a, b;

        public Pair(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }

}