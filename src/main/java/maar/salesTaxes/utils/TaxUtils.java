package maar.salesTaxes.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utilities to compute taxes.
 */
public final class TaxUtils {
    /**
     * The rounding precision
     */
    private static final BigDecimal PRECISION = new BigDecimal("0.05");

    /**
     * To compute percentages
     */
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    /**
     * Private constructor to prevent instantiation.
     */
    private TaxUtils() {
        // Do nothing
    }

    /**
     * Returns the amount after applying the provided taxes (amount * (1+tax)).
     *
     * @param amount The base amount.
     * @param tax    The taxes to apply.
     * @return The rounded amount after applying taxes.
     */
    public static BigDecimal applyTaxes(BigDecimal amount, BigDecimal tax) {
        return amount.add(round(amount.multiply(tax).divide(ONE_HUNDRED)));
    }

    /**
     * Rounds amount to the default PRECISION.
     *
     * @param amount The amount to apply rounding to.
     * @return The result of the rounding.
     */
    private static BigDecimal round(BigDecimal amount) {
        return amount.divide(PRECISION, 0, RoundingMode.UP).multiply(PRECISION).setScale(2, RoundingMode.HALF_UP);
    }
}
