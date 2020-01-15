package maar.salesTaxes.application;

import maar.salesTaxes.model.item.Item;
import maar.salesTaxes.model.item.TaxableItem;
import maar.salesTaxes.service.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to parse the input as a formatted string.
 */
public class InputParser {
    /**
     * The pattern to match each line of the input
     */
    private static final String LINE_REGEX = "(?<quantity>[0-9]+)\\s+(?<description>.+)\\s+at\\s+(?<price>[0-9]+(\\.[0-9]+)?)";

    /**
     * The pattern to be used to check the input
     */
    private static final Pattern PATTERN = Pattern.compile(LINE_REGEX);

    /**
     * Key to access group in the regular expression
     */
    private static final String QUANTITY = "quantity";
    /**
     * Key to access group in the regular expression
     */
    private static final String IMPORTED = "imported";
    /**
     * Key to access group in the regular expression
     */
    private static final String CATEGORY = "description";
    /**
     * Key to access group in the regular expression
     */
    private static final String PRICE = "price";

    /**
     * The service to know the category of each item
     */
    private final CategoryService categoryService;

    /**
     * Creates a new input parser to convert from the input text to a list of items.
     *
     * @param categoryService The service to know to which category each input element belongs.
     */
    public InputParser(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns the list of items parsed from the formatted input. Each line is expected to match the pattern "quantity
     * description at price".
     *
     * @param input The stream to read the input.
     * @return The list of items parsed from the formatted input.
     * @throws IOException
     */
    public List<Item> parseInput(InputStream input) throws IOException {
        List<Item> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    items.addAll(parseItem(line));
                }
                line = reader.readLine();
            }
        }

        return items;
    }

    /**
     * Returns the list of items read from a line. This may seem strange, but since the line contains the quantity, the
     * object is created as many times as instructed (all items created will be equal).
     *
     * @param line The input text line.
     * @return The list of items (at least one) corresponding to the line. If more than one are returned, they will all
     * be equal.
     * @throws IOException
     */
    private List<Item> parseItem(String line) throws IOException {
        Matcher matcher = PATTERN.matcher(line);
        List<Item> itemList = new ArrayList<>();

        /* Try to match the line and extract information from each of the groups */
        if (matcher.matches()) {
            String quantityStr = matcher.group(QUANTITY);
            String description = matcher.group(CATEGORY);
            String price = matcher.group(PRICE);

            try {
                int quantity = Integer.parseInt(quantityStr);

                for (int i = 0; i < quantity; i++) {
                    Item item;
                    switch (categoryService.getCategory(description)) {
                        case BOOK:
                        case MEDICINE:
                        case FOOD:
                            item = Item.builder()
                                       .description(description)
                                       .price(new BigDecimal(price))
                                       .imported(description.contains(IMPORTED))
                                       .build();
                            break;
                        case OTHER:
                        default:
                            item = TaxableItem.taxableBuilder()
                                              .description(description)
                                              .price(new BigDecimal(price))
                                              .imported(description.contains(IMPORTED))
                                              .build();
                            break;
                    }
                    itemList.add(item);
                }
            } catch (IllegalArgumentException e) {
                throw new IOException("Invalid quantity found: " + quantityStr);
            }
        } else {
            throw new IOException("Input line not matching expected format: " + line);
        }

        return itemList;
    }
}
