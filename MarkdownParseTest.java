import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testTestFile() {
        assertEquals(List.of("https://something.com", "some-thing.html"), readFile("test-file.md"));
    }

    @Test
    public void testNestedBreak() {
        assertEquals(List.of("https://www.youtube.com/"), readFile("nested-break-test.md"));
    }

    @Test
    public void testBreakingTest() {
        assertEquals(List.of("https://www.youtube.com/"), readFile("breaking-test.md"));
    }

    @Test
    public void testNewlineBreak() {
        assertEquals(List.of("https://www.youtube.com/"), readFile("newline-break-test.md"));
    }

    public ArrayList<String> readFile(String fileName) {
        Path filePath = Path.of(fileName);
        String content = null;
        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return MarkdownParse.getLinks(content);
    }
}