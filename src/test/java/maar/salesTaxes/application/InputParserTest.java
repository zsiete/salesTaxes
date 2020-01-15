package maar.salesTaxes.application;

import maar.salesTaxes.model.item.Item;
import maar.salesTaxes.model.item.TaxableItem;
import maar.salesTaxes.service.CategoryService;
import maar.salesTaxes.testUtils.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class InputParserTest {
    private InputParser inputParser;

    @Before
    public void setUp() {
        // For a real service, category service would have been mocked
        inputParser = new InputParser(new CategoryService());
    }

    @Test
    public void testInput1() throws IOException {
        String inputText = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85\n";

        Item book = Item.builder().description("book").price(new BigDecimal("12.49")).build();
        Item musicCD = TaxableItem.taxableBuilder().description("music CD").price(new BigDecimal("14.99")).build();
        Item chocolateBar = Item.builder().description("chocolate bar").price(new BigDecimal("0.85")).build();

        try (InputStream inputStream = IOUtils.getInput(inputText);) {
            List<Item> itemList = inputParser.parseInput(inputStream);
            assertThat(itemList, contains(book, musicCD, chocolateBar));
        }
    }

    @Test
    public void testInput2() throws IOException {
        String inputText = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50\n";

        Item chocolates = Item.builder()
                              .description("imported box of chocolates")
                              .imported(true)
                              .price(new BigDecimal("10.00"))
                              .build();
        Item bottle = TaxableItem.taxableBuilder()
                                 .description("imported bottle of perfume")
                                 .imported(true)
                                 .price(new BigDecimal("47.50"))
                                 .build();

        try (InputStream inputStream = IOUtils.getInput(inputText);) {
            List<Item> itemList = inputParser.parseInput(inputStream);
            assertThat(itemList, contains(chocolates, bottle));
        }
    }

    @Test
    public void testInput3() throws IOException {
        String inputText = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "1 box of imported chocolates at 11.25\n";

        Item importedBottle = TaxableItem.taxableBuilder()
                                         .description("imported bottle of perfume")
                                         .imported(true)
                                         .price(new BigDecimal("27.99"))
                                         .build();
        Item bottle = TaxableItem.taxableBuilder()
                                 .description("bottle of perfume")
                                 .price(new BigDecimal("18.99"))
                                 .build();
        Item pills = Item.builder().description("packet of headache pills").price(new BigDecimal("9.75")).build();
        Item chocolates = Item.builder()
                              .description("box of imported chocolates")
                              .imported(true)
                              .price(new BigDecimal("11.25"))
                              .build();

        try (InputStream inputStream = IOUtils.getInput(inputText);) {
            List<Item> itemList = inputParser.parseInput(inputStream);
            assertThat(itemList, contains(importedBottle, bottle, pills, chocolates));
        }
    }

    /**
     * Test that empty lines are ignored and the contents correctly parsed.
     *
     * @throws IOException
     */
    @Test
    public void testEmptyLines() throws IOException {
        String inputText = "\n\n1 book at 12.49\n\n" +
                "1 music CD at 14.99\n\n\n" +
                "1 chocolate bar at 0.85\n\n";

        Item book = Item.builder().description("book").price(new BigDecimal("12.49")).build();
        Item musicCD = TaxableItem.taxableBuilder().description("music CD").price(new BigDecimal("14.99")).build();
        Item chocolateBar = Item.builder().description("chocolate bar").price(new BigDecimal("0.85")).build();

        try (InputStream inputStream = IOUtils.getInput(inputText);) {
            List<Item> itemList = inputParser.parseInput(inputStream);
            assertThat(itemList, contains(book, musicCD, chocolateBar));
        }
    }

    @Test(expected = IOException.class)
    public void testWrongInput() throws IOException {
        String inputText = "this is wrong";

        try (InputStream inputStream = IOUtils.getInput(inputText);) {
            inputParser.parseInput(inputStream);
        }
    }
}
