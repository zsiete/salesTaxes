package maar.salesTaxes.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TaxUtilsTest {
    private static final BigDecimal ZERO_TAX = new BigDecimal("0");
    private static final BigDecimal TAX = new BigDecimal("10");

    /**
     * Test that applying 0 taxes leaves the input unchanged.
     */
    @Test
    public void testZeroTax() {
        BigDecimal input = new BigDecimal("1.498");

        assertThat(TaxUtils.applyTaxes(input, ZERO_TAX), equalTo(input));
    }

    /**
     * Test applying taxes with rounding.
     */
    @Test
    public void testRounding() {
        BigDecimal bd1 = new BigDecimal("0.01");
        BigDecimal bd2 = new BigDecimal("1");
        BigDecimal bd3 = new BigDecimal("14");
        BigDecimal bd4 = new BigDecimal("14.5");
        BigDecimal bd5 = new BigDecimal("14.6");
        BigDecimal bd6 = new BigDecimal("0.00");

        BigDecimal e1 = new BigDecimal("0.06");
        BigDecimal e2 = new BigDecimal("1.10");
        BigDecimal e3 = new BigDecimal("15.40");
        BigDecimal e4 = new BigDecimal("15.95");
        BigDecimal e5 = new BigDecimal("16.10");
        BigDecimal e6 = new BigDecimal("0.00");

        assertThat(TaxUtils.applyTaxes(bd1, TAX), equalTo(e1));
        assertThat(TaxUtils.applyTaxes(bd2, TAX), equalTo(e2));
        assertThat(TaxUtils.applyTaxes(bd3, TAX), equalTo(e3));
        assertThat(TaxUtils.applyTaxes(bd4, TAX), equalTo(e4));
        assertThat(TaxUtils.applyTaxes(bd5, TAX), equalTo(e5));
        assertThat(TaxUtils.applyTaxes(bd6, TAX), equalTo(e6));
    }
}
