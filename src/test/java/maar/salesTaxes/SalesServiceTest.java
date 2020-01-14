package maar.salesTaxes;

import maar.salesTaxes.model.Receipt;
import maar.salesTaxes.service.CartService;
import org.junit.Test;

public class SalesServiceTest {
    @Test
    public void testReceipt() {
        CartService service = new CartService();
        service.add(BookBuilder.newBuilder().name("").build());
        service.add();
        Receipt receipt = service.getReceipt();

        assertThat(receipt.getTotal(), isEquals(2341241));
        assertThat( receipt.getItems(), contains(
                hasProperty("name", allOf(is("foo"), assertThat(value is 111))),
                hasProperty("name", is("bar"))
        ));
    }
}
