//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    // checks if there is something in front of
    // pos that "looks like a link exists, we
    // still don't know if one actually exists yet
    public static boolean linkExists(String str, int pos) {
        return str.indexOf("[", pos) != -1 && str.indexOf("]", pos) != -1 && str.indexOf("(", pos) != -1 && str.indexOf(")", pos) != -1;
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length() && linkExists(markdown, currentIndex)) {
            //System.out.println(currentIndex);
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            while (markdown.indexOf("[", openBracket + 1) != -1 && markdown.indexOf("[", openBracket + 1) < closeBracket) {
                openBracket = markdown.indexOf("(", openBracket + 1);
            }
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            while (markdown.indexOf("(", openParen + 1) != -1 && markdown.indexOf("(", openParen + 1) < closeParen) {
               openParen = markdown.indexOf("(", openParen + 1);
            }
            if (closeBracket + 1 == openParen) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        //testIndividual
        testManyFileX();
    }

    public static void testManyFileX() throws IOException {
        for (int i = 2; i <= 8; i++) {
            System.out.println("test-file" + i + ": ");
            testIndividual("test" + File.separator + "test-file" + i + ".md");
            System.out.println();
        }
    }

    public static void testIndividual(String f) throws IOException {
        Path fileName = Path.of(f);
        String content = Files.readString(fileName);
        //System.out.println(content.length());
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
