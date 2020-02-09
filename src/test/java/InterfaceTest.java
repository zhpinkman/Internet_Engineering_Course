import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Logger;

public class InterfaceTest {
    @Test
    public void interfaceTest1(){
        final String FILE_IN = "src/test/InterfaceTest1";
        final String FILE_OUT = "src/test/out.out";
        final String FILE_EXPECTED_OUT = "src/test/InterfaceTest1.out";
        try {
            PrintStream out = new PrintStream(new FileOutputStream(FILE_OUT));
            InputStream in = new FileInputStream(FILE_IN);
            Interface.start(in, out);
            out.close();
            File file1 = new File(FILE_OUT);
            File file2 = new File(FILE_EXPECTED_OUT);
            boolean isTwoEqual = FileUtils.contentEquals(file1, file2);
            Assert.assertTrue("The files differ!", isTwoEqual);
//            Assert.assertEquals("The files differ!", FileUtils.readLines(file1), FileUtils.readLines(file2));
        }catch (Exception e){
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}
