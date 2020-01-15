package maar.salesTaxes.application;

import maar.salesTaxes.model.item.Receipt;
import maar.salesTaxes.model.item.ReceiptItem;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * This class provides a way to print the receipt to an output stream.
 */
public class OutputFormatter {
    /**
     * Format for each line of a receipt item
     */
    private static final String RECEIPT_ITEM_FORMAT = "%d %s: %s";

    /**
     * Format for the taxes summary
     */
    private static final String TAXES_FORMAT = "Sales Taxes: %s";

    /**
     * Format for the total summary
     */
    private static final String TOTAL_FORMAT = "Total: %s";

    /**
     * Zero for null values.
     */
    private static final BigDecimal ZERO = new BigDecimal("0.00");

    /**
     * Prints out the receipt in the provided output stream, showing the receipt items and the taxes and totals.
     *
     * @param receipt      The receipt to print.
     * @param outputStream The stream to print the receipt.
     * @throws IOException
     */
    public void print(Receipt receipt, OutputStream outputStream) throws IOException {
        try (PrintWriter pw = new PrintWriter(outputStream)) {
            if (receipt.getReceiptItemList() != null) {
                for (ReceiptItem receiptItem : receipt.getReceiptItemList()) {
                    pw.println(String.format(RECEIPT_ITEM_FORMAT, receiptItem.getQuantity(), receiptItem.getItem()
                                                                                                        .getDescription(), receiptItem
                            .getItem()
                            .getTaxedPrice()));
                }
            }

            BigDecimal taxes = receipt.getTaxes() != null ? receipt.getTaxes() : ZERO;
            pw.println(String.format(TAXES_FORMAT, taxes));

            BigDecimal total = receipt.getTotal() != null ? receipt.getTotal() : ZERO;
            pw.println(String.format(TOTAL_FORMAT, total));
        }
    }
}
