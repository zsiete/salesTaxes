package maar.salesTaxes.application;

import maar.salesTaxes.testUtils.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests the whole application from the entry point (excluding main).
 */
public class ApplicationIT {
    /**
     * Tests the provided inputs from the exercise.
     */
    @Test
    public void testInput1() throws IOException {
        testInput("/input1.txt", "/output1.txt");
        testInput("/input2.txt", "/output2.txt");
        testInput("/input3.txt", "/output3.txt");
    }

    /**
     * Common method to test that the result of running the application against the input file yields the content of the
     * output file.
     *
     * @param inputFile  Path to the input file (resource)
     * @param outputFile Path to the output file (resource)
     * @throws IOException
     */
    private void testInput(String inputFile, String outputFile) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            InputStream input = getClass().getResourceAsStream(inputFile);
            InputStream output = getClass().getResourceAsStream(outputFile);

            // Run the application that reads the input and prints to the output
            new Application().run(input, baos);

            // Check that the output contains what we expect
            assertThat(IOUtils.getString(baos.toByteArray()), equalTo(IOUtils.readInputStream(output)));
        }
    }
}
