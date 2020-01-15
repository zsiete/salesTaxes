package maar.salesTaxes.utils;

/**
 * This class contains constants useful for sales.
 */
public final class TaxConstants {

    /** Basic tax rate for taxable items. */
    public static final String BASIC_TAX = "10";

    /** Import duty for all imported items. */
    public static final String IMPORT_TAX = "5";

    /**
     * Prevent instantiation of constants class.
     */
    private TaxConstants() {
        // Do nothing
    }
}
