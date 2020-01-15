package maar.salesTaxes;

import maar.salesTaxes.model.item.ReceiptItem;
import maar.salesTaxes.model.item.Item;
import maar.salesTaxes.model.item.Receipt;
import maar.salesTaxes.model.item.TaxableItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class contains the test data for the different scenarios.
 */
public final class TestData {

    /* Data for case 1 */
    public static final String INPUT_1_STRING = "1 book at 12.49\n" +
            "1 music CD at 14.99\n" +
            "1 chocolate bar at 0.85\n";
    public static final Item INPUT_1_BOOK = Item.builder().description("book").price(new BigDecimal("12.49")).build();
    public static final Item INPUT_1_CD = TaxableItem.taxableBuilder()
                                                     .description("music CD")
                                                     .price(new BigDecimal("14.99"))
                                                     .build();
    public static final Item INPUT_1_CHOCOLATE = Item.builder()
                                                     .description("chocolate bar")
                                                     .price(new BigDecimal("0.85"))
                                                     .build();
    public static final ReceiptItem OUTPUT_1_BOOK = new ReceiptItem(INPUT_1_BOOK);
    public static final ReceiptItem OUTPUT_1_CD = new ReceiptItem(INPUT_1_CD);
    public static final ReceiptItem OUTPUT_1_CHOCOLATE = new ReceiptItem(INPUT_1_CHOCOLATE);
    public static final BigDecimal OUTPUT_1_TAXES = new BigDecimal("1.50");
    public static final BigDecimal OUTPUT_1_TOTAL = new BigDecimal("29.83");
    public static final String OUTPUT_1_STRING = "1 book: 12.49\n" +
            "1 music CD: 16.49\n" +
            "1 chocolate bar: 0.85\n" +
            "Sales Taxes: 1.50\n" +
            "Total: 29.83\n";

    /* Data for case 2 */
    public static final String INPUT_2_STRING = "1 imported box of chocolates at 10.00\n" +
            "1 imported bottle of perfume at 47.50\n";
    public static final Item INPUT_2_CHOCOLATE = Item.builder()
                                                     .description("imported box of chocolates")
                                                     .imported(true)
                                                     .price(new BigDecimal("10.00"))
                                                     .build();
    public static final Item INPUT_2_PERFUME = TaxableItem.taxableBuilder()
                                                          .description("imported bottle of perfume")
                                                          .imported(true)
                                                          .price(new BigDecimal("47.50"))
                                                          .build();
    public static final ReceiptItem OUTPUT_2_CHOCOLATE = new ReceiptItem(INPUT_2_CHOCOLATE);
    public static final ReceiptItem OUTPUT_2_PERFUME = new ReceiptItem(INPUT_2_PERFUME);
    public static final BigDecimal OUTPUT_2_TAXES = new BigDecimal("7.65");
    public static final BigDecimal OUTPUT_2_TOTAL = new BigDecimal("65.15");
    public static final String OUTPUT_2_STRING = "1 imported box of chocolates: 10.50\n" +
            "1 imported bottle of perfume: 54.65\n" +
            "Sales Taxes: 7.65\n" +
            "Total: 65.15\n";

    /* Data for case 3 */
    public static final String INPUT_3_STRING = "1 imported bottle of perfume at 27.99\n" +
            "1 bottle of perfume at 18.99\n" +
            "1 packet of headache pills at 9.75\n" +
            "1 box of imported chocolates at 11.25\n";
    public static final Item INPUT_3_IMPORTED_PERFUME = TaxableItem.taxableBuilder()
                                                                   .description("imported bottle of perfume")
                                                                   .imported(true)
                                                                   .price(new BigDecimal("27.99"))
                                                                   .build();
    public static final Item INPUT_3_PERFUME = TaxableItem.taxableBuilder()
                                                          .description("bottle of perfume")
                                                          .price(new BigDecimal("18.99"))
                                                          .build();
    public static final Item INPUT_3_PILLS = Item.builder()
                                                 .description("packet of headache pills")
                                                 .price(new BigDecimal("9.75"))
                                                 .build();
    public static final Item INPUT_3_CHOCOLATE = Item.builder()
                                                     .description("box of imported chocolates")
                                                     .imported(true)
                                                     .price(new BigDecimal("11.25"))
                                                     .build();
    public static final ReceiptItem OUTPUT_3_IMPORTED_PERFUME = new ReceiptItem(INPUT_3_IMPORTED_PERFUME);
    public static final ReceiptItem OUTPUT_3_PERFUME = new ReceiptItem(INPUT_3_PERFUME);
    public static final ReceiptItem OUTPUT_3_PILLS = new ReceiptItem(INPUT_3_PILLS);
    public static final ReceiptItem OUTPUT_3_CHOCOLATE = new ReceiptItem(INPUT_3_CHOCOLATE);
    public static final BigDecimal OUTPUT_3_TAXES = new BigDecimal("6.70");
    public static final BigDecimal OUTPUT_3_TOTAL = new BigDecimal("74.68");
    public static final String OUTPUT_3_STRING = "1 imported bottle of perfume: 32.19\n" +
            "1 bottle of perfume: 20.89\n" +
            "1 packet of headache pills: 9.75\n" +
            "1 box of imported chocolates: 11.85\n" +
            "Sales Taxes: 6.70\n" +
            "Total: 74.68\n";

    /**
     * Output for an empty receipt
     */
    public static final String OUTPUT_EMPTY = "Sales Taxes: 0.00\n" +
            "Total: 0.00\n";

    /**
     * Private constructor to prevent instantiation.
     */
    private TestData() {
        // Nothing to do
    }
}
