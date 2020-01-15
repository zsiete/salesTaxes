package maar.salesTaxes.application;

import maar.salesTaxes.testUtils.IOUtils;
import maar.salesTaxes.TestData;
import maar.salesTaxes.model.item.Receipt;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * Tests the OutputFormatter class.
 */
public class OutputFormatterTest {
    /**
     * The output formatter to test
     */
    private OutputFormatter outputFormatter;

    /**
     * Sets up the output formatter for the tests.
     */
    @Before
    public void setUp() {
        outputFormatter = new OutputFormatter();
    }

    /**
     * Tests that a receipt with the contents for input 1 yields the expected results.
     *
     * @throws IOException
     */
    @Test
    public void testOutput1() throws IOException {
        Receipt receipt = Mockito.mock(Receipt.class);
        when(receipt.getTaxes()).thenReturn(TestData.OUTPUT_1_TAXES);
        when(receipt.getTotal()).thenReturn(TestData.OUTPUT_1_TOTAL);
        when(receipt.getReceiptItemList())
                .thenReturn(Arrays.asList(TestData.OUTPUT_1_BOOK, TestData.OUTPUT_1_CD, TestData.OUTPUT_1_CHOCOLATE));
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            outputFormatter.print(receipt, baos);
            MatcherAssert.assertThat(IOUtils.getString(baos.toByteArray()), equalTo(TestData.OUTPUT_1_STRING));
        }
    }

    /**
     * Tests that a receipt with the contents for input 2 yields the expected results.
     *
     * @throws IOException
     */
    @Test
    public void testOutput2() throws IOException {
        Receipt receipt = Mockito.mock(Receipt.class);
        when(receipt.getTaxes()).thenReturn(TestData.OUTPUT_2_TAXES);
        when(receipt.getTotal()).thenReturn(TestData.OUTPUT_2_TOTAL);
        when(receipt.getReceiptItemList())
                .thenReturn(Arrays.asList(TestData.OUTPUT_2_CHOCOLATE, TestData.OUTPUT_2_PERFUME));
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            outputFormatter.print(receipt, baos);
            assertThat(IOUtils.getString(baos.toByteArray()), equalTo(TestData.OUTPUT_2_STRING));
        }
    }

    /**
     * Tests that a receipt with the contents for input 3 yields the expected results.
     *
     * @throws IOException
     */
    @Test
    public void testOutput3() throws IOException {
        Receipt receipt = Mockito.mock(Receipt.class);
        when(receipt.getTaxes()).thenReturn(TestData.OUTPUT_3_TAXES);
        when(receipt.getTotal()).thenReturn(TestData.OUTPUT_3_TOTAL);
        when(receipt.getReceiptItemList())
                .thenReturn(Arrays.asList(TestData.OUTPUT_3_IMPORTED_PERFUME, TestData.OUTPUT_3_PERFUME, TestData.OUTPUT_3_PILLS, TestData.OUTPUT_3_CHOCOLATE));
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            outputFormatter.print(receipt, baos);
            assertThat(IOUtils.getString(baos.toByteArray()), equalTo(TestData.OUTPUT_3_STRING));
        }
    }

    /**
     * Tests that a receipt with no contents yields the expected results.
     *
     * @throws IOException
     */
    @Test
    public void testEmptyReceipt() throws IOException {
        Receipt receipt = Mockito.mock(Receipt.class);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            outputFormatter.print(receipt, baos);
            assertThat(IOUtils.getString(baos.toByteArray()), equalTo(TestData.OUTPUT_EMPTY));
        }
    }
}
