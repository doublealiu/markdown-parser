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