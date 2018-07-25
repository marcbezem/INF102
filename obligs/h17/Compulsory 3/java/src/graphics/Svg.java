package graphics;

import maze.Maze;
import maze.Point;
import connectingToilets.Edge;
import connectingToilets.Toilet;
import connectingToilets.ToiletMap;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class Svg {

    private final static String HTML_FILE_NAME = "visualised";
    private final static String HTML_SUFFIX = ".html";

    public static void runSVG(String svgContent) {

        File htmlFile;
        try {
            htmlFile = File.createTempFile(HTML_FILE_NAME, HTML_SUFFIX);
            // htmlFile.deleteOnExit();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), StandardCharsets.UTF_8));
            writer.write(svgContent);
            writer.close();
            Desktop.getDesktop().browse(htmlFile.toURI());
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Takes a maze and generates svg to draw the maze and the path
     *
     * @param maze
     * @return
     */
    public static String buildSvgFromMaze(Maze maze) {
        final int SIZE = 20;
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append(svgBeginning(maze.getWidth() * SIZE, maze.getHeight() * SIZE));

        StringBuilder svgContent = new StringBuilder();
        for (Point wall : maze.getWalls()) {
            String rect = String.format(
                    "<rect width=\"%d\" height=\"%d\" x=\"%d\" y=\"%d\" style=\"fill:rgb(0,0,0);stroke-width:1;stroke:rgb(0,0,0)\" />\n",
                    SIZE, SIZE, wall.getX() * SIZE, wall.getY() * SIZE);
            svgContent.append(rect);
        }

        String player = String.format(
                "<circle r=\"%d\" cx=\"%d\" cy=\"%d\" style=\"fill:rgb(0,0,255);stroke-width:5;stroke:rgb(0,0,0)\" />\n",
                SIZE / 3, maze.getPlayer().getX() * SIZE + SIZE / 2, maze.getPlayer().getY() * SIZE + SIZE / 2);
        svgContent.append(player);

        String goal = String.format(
                "<circle r=\"%d\" cx=\"%d\" cy=\"%d\" style=\"fill:rgb(0,255,0);stroke-width:5;stroke:rgb(0,0,0)\" />\n",
                SIZE / 3, maze.getGoal().getX() * SIZE + SIZE / 2, maze.getGoal().getY() * SIZE + SIZE / 2);
        svgContent.append(goal);

        StringBuilder path = new StringBuilder();
        for (Point p : Maze.solve(maze)) {
            path.append(String.format(
                    "<circle r=\"%d\" cx=\"%d\" cy=\"%d\" style=\"fill:rgb(0,255,0);stroke-width:0;stroke:rgb(0,0,0)\" />\n",
                    SIZE / 3, p.getX() * SIZE + SIZE / 2, p.getY() * SIZE + SIZE / 2));
        }
        svgContent.append(path.toString());

        htmlContent.append(svgContent);
        htmlContent.append(svgEnd());
        return htmlContent.toString();
    }

    /**
     * Takes a map of hoytek and generates svg to draw employees and edges between them
     *
     * @param hoytek
     * @return
     */
    public static String buildSvgFromScienceEmployees(ToiletMap hoytek) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append(svgBeginning(2100, 1600));


        for (Edge e : hoytek.getConnections()) {
            int x1 = (int) e.getToiletA().getX(), y1 = (int) e.getToiletA().getY();
            int x2 = (int) e.getToiletB().getX(), y2 = (int) e.getToiletB().getY();
            htmlContent.append(svgLine(x1, y1, x2, y2));
        }

        for (Toilet e : hoytek.getToilets()) {
            htmlContent.append(svgCircle(2, (int) e.getX(), (int) e.getY(), "(0,0,0)", 0, "(0,0,0)"));
            htmlContent.append(svgText(e.getName(), (int) (e.getX() + 3), (int) (e.getY() + 10)));
        }

        htmlContent.append(svgEnd());
        return htmlContent.toString();
    }

    private static String svgBeginning(int width, int height) {
        String style = "<style>\n" +
                ".svgtext {font-size: 10pt } .svgtext:hover {font-size: 20pt;}\n" +
                ".svgEdge {stroke-width:2} .svgEdge:hover {stroke-width:5}\n" +
                "</style>\n";

        return "<!DOCTYPE html>\n" + "<html><head>\n" + "<title>Visualised</title>\n" + style + "</head>\n" + "<meta charset=\"UTF-8\">\n" + "<body>\n"
                + String.format("<svg width=\"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/Svg\">\n",
                width, height);
    }

    private static String svgEnd() {
        return "</svg>\n</body>\n</html>";
    }

    private static String svgCircle(int radius, int x, int y, String fill, int strokeWidth, String strokeColor) {
        return String.format("<circle r=\"%d\" cx=\"%d\" cy=\"%d\" style=\"fill:rgb%s;stroke-width:%d;stroke:rgb%s\" />\n", radius, x, y, fill, strokeWidth, strokeColor);
    }

    private static String svgText(String text, int x, int y) {
        System.out.println(text);
        return String.format("<text x=\"%d\" y=\"%d\" font-family=\"Verdana\" class=\"svgtext\">%s</text>\n", x, y, text);
    }

    private static String svgLine(int x1, int y1, int x2, int y2) {
        return String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:rgb(255,0,0);\" class=\"svgEdge\" />\n", x1, y1, x2, y2);
    }
}