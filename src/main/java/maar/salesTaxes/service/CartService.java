package maar.salesTaxes.service;

import maar.salesTaxes.model.item.Item;
import maar.salesTaxes.model.item.Receipt;
import maar.salesTaxes.model.item.ReceiptItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service to represent the shopping cart. It holds the list of selected items and allows to add new items.
 */
public class CartService {
    /**
     * The list of items
     */
    private List<ReceiptItem> receiptItemList = new ArrayList<>();

    /**
     * Adds a new item to the receipt items list. If the item is already in the list, the quantity is increased
     * instead.
     *
     * @param item The item to add.
     */
    public void add(Item item) {
        Optional<ReceiptItem> conceptOptional = receiptItemList.stream()
                                                               .filter(concept -> concept.getItem().equals(item))
                                                               .findFirst();

        if (conceptOptional.isPresent()) {
            ReceiptItem receiptItem = conceptOptional.get();
            receiptItem.setQuantity(receiptItem.getQuantity() + 1);
        } else {
            receiptItemList.add(new ReceiptItem(item));
        }
    }

    /**
     * Returns a receipt for the items in the cart.
     *
     * @return The computed receipt.
     */
    public Receipt getReceipt() {
        Receipt receipt = new Receipt();

        receipt.getReceiptItemList().addAll(receiptItemList);

        return receipt;
    }
}
