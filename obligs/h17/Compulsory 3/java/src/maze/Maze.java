package maze;

import graphics.Svg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Maze {

	private final static String MAZE_FILE = "maze/maze.txt"; // or maze.txt

	private Set<Point> walls = new HashSet<>();
	private Point player;
	private Point goal;
	private int width, height;

	public static void main(String[] args) {
		Maze maze = new Maze(MAZE_FILE);
		String finalHtmlContent = Svg.buildSvgFromMaze(maze);
		Svg.runSVG(finalHtmlContent);
	}

    public Maze(Set<Point> walls, Point player, Point goal, int width, int height) {
        this.walls = walls;
        this.player = player;
        this.goal = goal;
        this.width = width;
        this.height = height;
    }

    public Maze(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream resource = classloader.getResourceAsStream(fileName)) {
		    if (resource == null) {
		        throw new IllegalArgumentException("File is missing");
            }
			List<String> lines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines()
					.collect(Collectors.toList());
		    int[] dimensions = Arrays.stream(lines.get(0).split(" ")).mapToInt(Integer::parseInt).toArray();
		    width = dimensions[0]; height = dimensions[1];
			lines.remove(0);
			for (int y = 0; y < lines.size(); y++) {
				char[] chars = lines.get(y).toCharArray();
				for (int x = 0; x < chars.length; x += 2) {
					if (chars[x] == '#')
						walls.add(new Point(x / 2, y));
					else if (chars[x] == 'S')
						player = new Point(x / 2, y);
					else if (chars[x] == 'E') {
						goal = new Point(x / 2, y);
					}
				}
			}
			if (player == null)
				throw new IllegalStateException("Maze has no start position.");
			else if (goal == null)
				throw new IllegalStateException("Maze has no end position.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* *** SOLUTION! *** */

    /**
     * Solves the maze
     * @return A list of points showing where the player needs to go to reach the end.
	 * The points should be in the correct order, meaning the first point in the
     * list should be next to the player.
     */
	public static List<Point> solve(Maze m) {
		boolean[][] visited = new boolean[m.width][m.height];
		Point[][] parent = new Point[m.width][m.height];
		Queue<Point> Q = new ArrayDeque<>();
		Q.add(m.player);
		Point current;
		while (true) {
			if (Q.isEmpty())
			    throw new IllegalStateException("No possible solution for this maze");
			current = Q.poll();
			if (current.equals(m.goal))
				break;
			int x = current.getX(), y = current.getY();
			visited[x][y] = true;
			List<Point> neighbours = Arrays.asList(new Point(x + 1, y), new Point(x, y + 1), new Point(x - 1, y),
					new Point(x, y - 1));
			for (Point p : neighbours) {
				int px = p.getX(), py = p.getY();
				if (px >= 0 && px < m.width && py >= 0 && py < m.height && !m.walls.contains(p) && !visited[px][py]) {
                    Q.add(p);
					parent[px][py] = current;
				}
			}
		}

		List<Point> backTrack = new ArrayList<>();
		while (current != null) {
			backTrack.add(current);
			current = parent[current.x][current.y];
		}
		backTrack.remove(m.player);
		return IntStream.range(0, backTrack.size())
                .map(i -> backTrack.size() - 1 - i)
                .mapToObj(backTrack::get)
                .collect(Collectors.toList());
	}

	public Set<Point> getWalls() {
		return walls;
	}

	public Point getPlayer() {
		return player;
	}


	public Point getGoal() {
		return goal;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


}
