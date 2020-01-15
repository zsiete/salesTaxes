package maar.salesTaxes.model.item;

import lombok.*;
import maar.salesTaxes.utils.TaxConstants;
import maar.salesTaxes.utils.TaxUtils;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * An Item has a price and may be imported. This class returns the base price as well as the taxed price.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Item {
    /**
     * The description of the item
     */
    private String description;

    /**
     * The base price (before taxes)
     */
    private BigDecimal price;

    /**
     * If this is an imported item
     */
    private boolean imported;

    /**
     * Returns the taxes applied to the base price.
     *
     * @return The taxes applied.
     */
    public BigDecimal getNetTaxes() {
        return getTaxedPrice().subtract(price);
    }

    /**
     * Returns the price after applying taxes.
     *
     * @return The price after applying taxes.
     */
    public BigDecimal getTaxedPrice() {
        return TaxUtils.applyTaxes(price, getTaxRate());
    }

    /**
     * Returns the applicable tax rate. By default items may be taxed if they are imported.
     *
     * @return The applicable tax rate.
     */
    protected BigDecimal getTaxRate() {
        return imported ? new BigDecimal(TaxConstants.IMPORT_TAX) : new BigDecimal("0");
    }
}
