package tree;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by knutandersstokke on 15 15.10.2017.
 */
public class PrintTree {

    private final static int INDENTATION_SIZE = 2;

    public static void main(String[] args) {
        String inputFile = args[0];
        String inputContent = new In(inputFile).readAll();
        System.out.println(formatStringToTree(inputContent));
    }

    public static String formatStringToTree(String inputContent) {
        Map<Integer, Set<Integer>> G = new HashMap<>();
        Map<Integer, String> idToFile = new HashMap<>();
        buildTree(inputContent, G, idToFile);
        return TreeToString(G, idToFile, 0, 0);
    }

    public static String TreeToString(Map<Integer, Set<Integer>> G, Map<Integer, String> idToFile, int fileId, int level) {

        StringBuilder builder = new StringBuilder();
        builder.append(new String(new char[level * INDENTATION_SIZE]).replace("\0", " "));
        builder.append("'-");
        builder.append(idToFile.get(fileId));
        builder.append('\n');

        Set<Integer> setOfChildren = G.get(fileId);
        if (setOfChildren == null || setOfChildren.isEmpty())
            return builder.toString();

        ArrayList<Integer> children = new ArrayList<>(setOfChildren);
        children.sort(Comparator.comparing(idToFile::get));

        for (int n : children) {
            builder.append(TreeToString(G, idToFile, n, level + 1));
        }

        return builder.toString();

    }

    public static void buildTree(String input, Map<Integer, Set<Integer>> G, Map<Integer, String> idToFile) {
        String[] inputLines = input.split("\n");
        int N = Integer.parseInt(inputLines[0]);
        String[] listOfFilenames = inputLines[1].split(" ");
        for (int i=0; i<N; i++) {
            idToFile.put(i, listOfFilenames[i]);
        }
        for (int i=2; i<inputLines.length; i++) {
            String[] folderContent = inputLines[i].split(" ");
            int currentFolderId = Integer.parseInt(folderContent[0]);
            G.putIfAbsent(currentFolderId, new HashSet<>()); // ifAbsent not neccessesary as one folder is only listed once
            Set<Integer> currentFolder =  G.get(currentFolderId);

            Arrays
                    .stream(folderContent)
                    .skip(2)
                    .mapToInt(Integer::parseInt)
                    .forEach(currentFolder::add);
        }
    }

}
