import converter.logic.ExcellWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExcellTest {
    private ExcellWriter excellWriter;
    private static String FILE_NAME = "temp.xlsx";
    private String fileLocation;

    @Before
    public void generateExcelFile() throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;

        excellWriter = new ExcellWriter();
//        excellWriter.write();
    }

    @Test
    public void read() throws IOException {
        Map<Integer, List<String>> data = excellWriter.read(fileLocation);

        assertEquals("ID", data.get(0).get(0));
        assertEquals("Name", data.get(0).get(1));

        assertEquals("1", data.get(1).get(0));
        assertEquals("Noah", data.get(1).get(1));
    }

    @After
    public void cleanup() {
        File testFile = new File(fileLocation);
        if(testFile.exists()) {
            testFile.delete();
        }
    }
}
