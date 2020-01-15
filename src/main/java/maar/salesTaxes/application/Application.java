package maar.salesTaxes.application;

import maar.salesTaxes.service.CartService;
import maar.salesTaxes.service.CategoryService;

import java.io.*;

import static java.lang.System.exit;

/**
 * Main entry point of the application. It receives a path to an input file as an argument, and prints to the standard
 * output the contents of the generated receipt.
 */
public class Application {
    /**
     * Main entry point. Checks arguments and prepares everything to start.
     *
     * @param args Expected 1 argument: path to the input file.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Unexpected number of arguments\nUsage: salesTax <output_file>");
            exit(1);
        }

        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            System.out.println(String.format("Input file %s does not exist", inputFile.getAbsolutePath()));
            exit(1);
        }

        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            new Application().run(inputStream, System.out);
        } catch (IOException e) {
            System.out.println("Unable to produce receipt from inputs; cause: " + e.getMessage());
            exit(1);
        }
    }

    /**
     * Actual method that reads the provided input and writes to the selected output.
     *
     * @param inputStream  The input stream containing the input list of items.
     * @param outputStream The output stream to write the contents of the receipt.
     * @throws IOException
     */
    public void run(InputStream inputStream, OutputStream outputStream) throws IOException {
        InputParser parser = new InputParser(new CategoryService());
        OutputFormatter outputFormatter = new OutputFormatter();

        CartService cartService = new CartService();

        // Add all items received from the parser to the cart
        parser.parseInput(inputStream).stream().forEach(cartService::add);

        // Print the receipt to the output stream
        outputFormatter.print(cartService.getReceipt(), outputStream);
    }
}
