package maar.salesTaxes.model.item;

import lombok.*;

/**
 * An item of a receipt. Contains an item and its quantity.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class ReceiptItem {
    /**
     * The item
     */
    private Item item;

    /**
     * The number of items selected
     */
    private int quantity;

    /**
     * Creates a receipt item. By default the quantity is set to 1.
     *
     * @param item The item
     */
    public ReceiptItem(Item item) {
        this.item = item;
        quantity = 1;
    }
}
