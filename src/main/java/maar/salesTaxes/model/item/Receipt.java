package maar.salesTaxes.model.item;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A receipt contains a list of items in a certain quantity.
 */
@Getter
@Setter
public class Receipt {
    /**
     * The list of items
     */
    private List<ReceiptItem> receiptItemList;

    /**
     * Creates a new empty receipt.
     */
    public Receipt() {
        receiptItemList = new ArrayList<>();
    }

    /**
     * Returns the total amount to pay for all the items, after taxes have been applied.
     *
     * @return The total amount for this receipt.
     */
    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0.0d);

        for (ReceiptItem receiptItem : receiptItemList) {
            total = total.add(receiptItem.getItem()
                                         .getTaxedPrice()
                                         .multiply(new BigDecimal(receiptItem.getQuantity())));
        }

        return total;
    }

    /**
     * Returns the total amount of taxes applied for the items in this receipt.
     *
     * @return The taxes applied.
     */
    public BigDecimal getTaxes() {
        BigDecimal taxes = new BigDecimal(0.0d);

        for (ReceiptItem receiptItem : receiptItemList) {
            taxes = taxes.add(receiptItem.getItem().getNetTaxes().multiply(new BigDecimal(receiptItem.getQuantity())));
        }

        return taxes;
    }
}
