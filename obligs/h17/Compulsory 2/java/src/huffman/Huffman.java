package huffman;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.princeton.cs.algs4.In;
import org.apache.commons.lang3.mutable.MutableInt;

public class Huffman {

    public static boolean isUnitTesting = false;

    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("Please provide filename of file to be encoded");
        String inputFile = args[0];
        String content = new In(inputFile).readAll();
        if (inputFile.endsWith(".txt")) {
            encode(content, inputFile);
            System.out.println("Compression done");
        } else if (inputFile.endsWith(".cmp")) {
            System.out.println("decoded text:");
            System.out.println(decode(content));
        } else {
            throw new IllegalArgumentException("Please provide a .txt file or a compressed .cmp file");
        }
    }

    /*
     * TODO: Replace all or parts of this code to use your huffman implementation to encode a file
     */
    public static void encode(String content, String originalFileName) {
        System.out.println("File is readed, starting compression..");
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(originalFileName + ".cmp"), "UTF-8"));

            System.out.println("Finding available ASCII-chars..");
            int max_range = isUnitTesting ? 126 : (int) Math.pow(2, 8) - 1;
            Set<String> availableSymbols = IntStream
                    .rangeClosed(33, max_range)
                    .mapToObj(c -> "" + (char) c)
                    .collect(Collectors.toSet());
            availableSymbols.remove("=");

            content
                    .chars()
                    .distinct()
                    .mapToObj(c -> "" + c)
                    .forEach(availableSymbols::remove);


            System.out.println("Counting words..");
            Map<String, MutableInt> map = new ConcurrentHashMap<>();
            Arrays.stream(content.split("\n"))
                    .parallel()
                    .filter(s -> s.length() > 0)
                    .forEach(l -> Arrays.stream(l.split(" "))
                            .forEach(w -> increase(map, w)));

            System.out.println("Sorting on frequency * word_length..");
            List<Map.Entry<String, MutableInt>> wordFreqs = map.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Comparator.comparing(a -> a.getKey().length() * a.getValue().toInteger())))
                    .collect(Collectors.toList());

            System.out.println("Setting up translation table..");
            Map<String, String> trans = new ConcurrentHashMap<>();
            availableSymbols.stream().limit(wordFreqs.size()).forEach(c -> insertSymbol(c, trans, wordFreqs));

            System.out.println("Compressing..");
            final String result = Arrays.stream(content.split("\n", -1)).parallel()
                    .map(line -> Arrays.stream(line.split(" ", -1))
                            .map(w -> replaceWord(w, trans))
                            .collect(Collectors.joining(" ")))
                    .collect(Collectors.joining("\n"));

            String transString = trans.entrySet().stream().map(e -> e.getKey() +" " + e.getValue()).collect(Collectors.joining(" "));

            System.out.println("Writing compressed content to file..");
            writer.write(transString + "\n****\n" + result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String replaceWord(String w, Map<String, String> trans) {
        return trans.getOrDefault(w, w);
    }

    private static void insertSymbol(String c, Map<String, String> trans, List<Map.Entry<String, MutableInt>> wordFreqs) {
        trans.put(wordFreqs.get(0).getKey(), c);
        wordFreqs.remove(0);
    }

    private static void increase(Map<String, MutableInt> map, String w) {
        MutableInt count = map.get(w);
        if (count == null) {
            map.putIfAbsent(w, new MutableInt());
            count = map.get(w);
        }
        count.increment();
    }

    /*
     * TODO: Replace all or parts of this code to use your huffman implementation to decode a file
     */
    public static String decode(String content) {
        String[] lines = content.split("\n", -1);
        Map<String, String> trans = getTranslationTable(lines[0]);

        return Arrays.stream(lines)
                .skip(2)
                .map(l -> Arrays.stream(l.split(" ", -1)).map(w -> replaceWord(w, trans)).collect(Collectors.joining(" ")))
                .collect(Collectors.joining("\n"));
    }

    private static Map<String, String> getTranslationTable(String s) {
        Map<String, String> trans = new HashMap<>();
        String[] transWord = s.split(" ");
        for (int i = 0; i < transWord.length; i+=2) {
            trans.put(transWord[i+1], transWord[i]);
        }
        return trans;
    }

    private static void printTranslationTable(Map<String, String> trans) {
        System.out.println();
        System.out.println(trans.keySet().stream().map(w -> w + ":" + w.charAt(0) + ":" + (int)w.charAt(0) + "\n"
                + trans.get(w) + ":" + (int)trans.get(w).charAt(0) ).collect(Collectors.joining(" ")));
        System.out.println();
    }

}
