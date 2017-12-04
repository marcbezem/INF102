
import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import helpers.FileReader;
import huffman.Huffman;

public class HuffmanTest {

    private final String DELIMITER = "****";
    private final FileReader fileReader = new FileReader();
    private final boolean DEBUG = false;

    @Before
    public void setup() {
        Huffman.isUnitTesting = true;
    }

    @Test
    public void testOneLineFile() {
        testHuffman("oneLine.txt");
    }

    @Test
    public void testTwoLinesFile() {
        testHuffman("twoLines.txt");
    }

    @Test
    public void testSmallText() {
        testHuffman("lorem.txt");
    }

    @Test
    public void testEmptyLineBetweenTwoLines() {
        testHuffman("emptyLineBetweenTwoLines.txt");
    }

    public void testLeipzig1M() {
        testHuffman("leipzig1M.txt");
    }


    private void testHuffman(String originalFile) {
        String originalContent = fileReader.getFile(originalFile);
        Huffman.encode(originalContent, originalFile);

        String[] linesOfCmp = fileReader.getFile(getDefaultAbsolutePath() + originalFile + ".cmp").split("\n");
        int delLine = findLineWithDelimiter(linesOfCmp);
        assertNotEquals(delLine, -1);

        String content = new FileReader().getFile(getDefaultAbsolutePath() + originalFile + ".cmp");
        String decoded = Huffman.decode(content);

        if (DEBUG)
            printOrigAndDecoded(originalContent, decoded);

        assertEquals(originalContent, decoded);
    }

    private void printOrigAndDecoded(String original, String decoded) {
        System.out.println("\n******************\n");
        System.out.println(original);
        System.out.println("----");
        System.out.println(decoded);

    }

    private int findLineWithDelimiter(String[] lines) {
        for (int i = 0; i < lines.length; i++)
            if (lines[i].equals(DELIMITER))
                return i + 1;
        return -1;
    }

    private String getDefaultAbsolutePath() {
        String absPath = new File(".").getAbsolutePath(); // a little hack...
        absPath = absPath.substring(0, absPath.length() - 1);
        return absPath;
    }

}