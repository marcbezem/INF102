package connectingToilets;

import graphics.Svg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by knutandersstokke on 16 16.10.2017.
 */
public class ConnectingToilets {

    private final static String TOILET_FILE = "connectingToilets/australian_toilet_map.txt";

    public static void main(String[] args) {
        Set<Toilet> toilets = readToiletsFromFile(TOILET_FILE);
        ToiletMap mapOfToilets = new ToiletMap(toilets, connectToilets(toilets));
        Svg.runSVG(Svg.buildSvgFromScienceEmployees(mapOfToilets));
    }

    public static Set<Toilet> readToiletsFromFile(String toiletFile) {
        List<String> lines = readLines(TOILET_FILE);
        if (lines == null) {
            System.out.print("An error ocurred trying to read " + TOILET_FILE + ". Check that the file exist.");
        }
        return lines.stream().map(ConnectingToilets::lineToToilet).collect(Collectors.toSet());

    }

    public static Set<Edge> connectToilets(Set<Toilet> toilets) {
        Set<Edge> MST = new HashSet<>();

        // Build graph
        Map<String, Set<Edge>> G = new HashMap<>();
        for (Toilet t : toilets) {
            Set<Edge> currentSet = new HashSet<>();
            G.put(t.getName(), currentSet);
            for (Toilet t2 : toilets) {
                if (t.getName().equals(t2.getName()))
                    continue;
                currentSet.add(new Edge(t, t2, dist(t, t2)));
            }
            System.out.println(G.size());
        }

        PriorityQueue<Edge> Q = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        String start = toilets.stream().findAny().get().getName();

        visited.add(start);
        Q.addAll(G.get(start));
        while (MST.size() < toilets.size() - 1) {
            Edge currentEdge = Q.poll();
            if (visited.contains(currentEdge.getToiletA().getName()) && visited.contains(currentEdge.getToiletB().getName()))
                continue;
            MST.add(currentEdge);
            System.out.println(MST.size());
            visited.add(currentEdge.getToiletB().getName());
            Q.addAll(G.get(currentEdge.getToiletB().getName()));
        }

        return MST;
    }

    private static Toilet lineToToilet(String line) {
        String[] fields = line.split(";");
        String name = fields[0];
        double x = Double.parseDouble(fields[1]);
        double y = Double.parseDouble(fields[2]);
        return new Toilet(name, x, y);
    }

    private static List<String> readLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream resource = classloader.getResourceAsStream(fileName)) {
            if (resource == null) {
                System.out.println("File is missing!");
                return null;
            }
            return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double dist(Toilet a, Toilet b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
