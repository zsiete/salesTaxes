package maar.salesTaxes.testUtils;

import java.io.*;

/**
 * Utilities to read and write strings.
 */
public final class IOUtils {
    /**
     * Private to prevent instantiation.
     */
    private IOUtils() {
        // Do nothing
    }

    /**
     * Return The string read from the given bytes.
     *
     * @param inputBytes The input byte array to read as a stream.
     * @return The string.
     * @throws IOException
     */
    public static String getString(byte[] inputBytes) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);
             BufferedReader br = new BufferedReader(new InputStreamReader(bais));) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
        }

        return sb.toString();
    }

    /**
     * Converts the input string to an input stream.
     *
     * @param inputString The input string.
     * @return The input stream to read the string.
     * @throws IOException
     */
    public static InputStream getInput(String inputString) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintWriter pw = new PrintWriter(baos);) {
            pw.print(inputString);
            pw.flush();
            return new ByteArrayInputStream(baos.toByteArray());
        }
    }
}
