package maar.salesTaxes.model.item;

import lombok.*;
import maar.salesTaxes.utils.TaxConstants;

import java.math.BigDecimal;

/**
 * A taxable item is an item that has associated a fixed tax rate.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TaxableItem extends Item {

    /**
     * Creates a new taxable item.
     *
     * @param description The description of the item.
     * @param price       The base prace of the item.
     * @param imported    Whether the item is imported or not.
     */
    @Builder(builderMethodName = "taxableBuilder")
    public TaxableItem(String description, BigDecimal price, boolean imported) {
        super(description, price, imported);
    }

    /**
     * Returns the tax rate applicable to this item. This returns the fixed tax rate plus any applicable tax rate of the
     * base item.
     *
     * @return The tax rate for this item.
     */
    @Override
    protected BigDecimal getTaxRate() {
        return super.getTaxRate().add(new BigDecimal(TaxConstants.BASIC_TAX));
    }
}
