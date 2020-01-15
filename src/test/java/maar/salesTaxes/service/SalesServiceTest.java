package maar.salesTaxes.service;

import maar.salesTaxes.TestData;
import maar.salesTaxes.model.item.Receipt;
import maar.salesTaxes.model.item.ReceiptItem;
import maar.salesTaxes.service.CartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Tests the sales service against a set of input items and checks the resulting receipt.
 */
public class SalesServiceTest {
    /**
     * The object to test
     */
    private CartService cartService;

    /**
     * Sets up the service to test
     */
    @Before
    public void setUp() {
        cartService = new CartService();
    }

    /**
     * Tests the input case 1.
     */
    @Test
    public void testReceipt1() {
        cartService.add(TestData.INPUT_1_BOOK);
        cartService.add(TestData.INPUT_1_CD);
        cartService.add(TestData.INPUT_1_CHOCOLATE);

        Receipt receipt = cartService.getReceipt();

        assertThat(receipt.getTotal(), equalTo(TestData.OUTPUT_1_TOTAL));
        assertThat(receipt.getTaxes(), equalTo(TestData.OUTPUT_1_TAXES));
        assertThat(receipt.getReceiptItemList(), contains(TestData.OUTPUT_1_BOOK, TestData.OUTPUT_1_CD, TestData.OUTPUT_1_CHOCOLATE));
    }

    /**
     * Tests the input case 2.
     */
    @Test
    public void testReceipt2() {
        cartService.add(TestData.INPUT_2_CHOCOLATE);
        cartService.add(TestData.INPUT_2_PERFUME);

        Receipt receipt = cartService.getReceipt();

        assertThat(receipt.getTotal(), equalTo(TestData.OUTPUT_2_TOTAL));
        assertThat(receipt.getTaxes(), equalTo(TestData.OUTPUT_2_TAXES));
        assertThat(receipt.getReceiptItemList(), contains(TestData.OUTPUT_2_CHOCOLATE, TestData.OUTPUT_2_PERFUME));
    }

    /**
     * Tests the input case 3.
     */
    @Test
    public void testReceipt3() {
        cartService.add(TestData.INPUT_3_IMPORTED_PERFUME);
        cartService.add(TestData.INPUT_3_PERFUME);
        cartService.add(TestData.INPUT_3_PILLS);
        cartService.add(TestData.INPUT_3_CHOCOLATE);

        Receipt receipt = cartService.getReceipt();

        assertThat(receipt.getTotal(), equalTo(TestData.OUTPUT_3_TOTAL));
        assertThat(receipt.getTaxes(), equalTo(TestData.OUTPUT_3_TAXES));
        assertThat(receipt.getReceiptItemList(), contains(TestData.OUTPUT_3_IMPORTED_PERFUME, TestData.OUTPUT_3_PERFUME, TestData.OUTPUT_3_PILLS, TestData.OUTPUT_3_CHOCOLATE));
    }

    /**
     * Tests adding several of the same items
     */
    @Test
    public void testQuantity() {
        cartService.add(TestData.INPUT_3_IMPORTED_PERFUME);
        cartService.add(TestData.INPUT_3_PERFUME);
        cartService.add(TestData.INPUT_3_IMPORTED_PERFUME);
        cartService.add(TestData.INPUT_3_PERFUME);

        Receipt receipt = cartService.getReceipt();
        ReceiptItem importedPerfume = new ReceiptItem(TestData.INPUT_3_IMPORTED_PERFUME, 2);
        ReceiptItem perfume = new ReceiptItem(TestData.INPUT_3_PERFUME, 2);

        BigDecimal expectedTotal = new BigDecimal("106.16");
        BigDecimal expectedTaxes = new BigDecimal("12.20");

        assertThat(receipt.getTotal(), equalTo(expectedTotal));
        assertThat(receipt.getTaxes(), equalTo(expectedTaxes));
        assertThat(receipt.getReceiptItemList(), contains(importedPerfume, perfume));
    }
}
