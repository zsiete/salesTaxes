package maar.salesTaxes;

import java.util.Arrays;

public final class TestData {
    public static final String BOOK = "book";
    public static final String MUSIC_CD = "music CD";
    public static final String CHOCOLATE_BAR = "chocolate bar";
    public static final String BOX_OF_CHOCOLATES = "box of chocolates";
    public static final String BOTTLE_OF_PERFUME = "bottle of perfume";
    public static final String PACKET_OF_PILLS = "packet of headache pills";

    private TestData() {
        // Nothing to do
    }

    public static List<Item> getInput1() {
        return Arrays.asList(ItemBuilder.newBuilder().name("book").price(12.49).taxes(false).build(),
                ItemBuilder.newBuilder().name("music CD").price(14.99).build(),
                ItemBuilder.newBuilder().name("chocolate bar").price(0.85).taxes(false).build());
    }

    public static List<Item> getInput2() {
        return Arrays.asList(ItemBuilder.newBuilder().name("box of chocolates").price(12.49).taxes(false).build(),
                ItemBuilder.newBuilder().name("bottle of perfume").price(14.99).build());
    }

    public static List<Item> getInput3() {
        return Arrays.asList(ItemBuilder.newBuilder().name(BOTTLE_OF_PERFUME).price(27.99).build(),
                ItemBuilder.newBuilder().name(BOTTLE_OF_PERFUME).price(18.99).imported(true).build(),
                ItemBuilder.newBuilder().name(PACKET_OF_PILLS).price(9.75).taxes(false).build(),
                ItemBuilder.newBuilder().name(BOX_OF_CHOCOLATES).price(11.25).taxes(false).build());
    }

    public static List<Item> getRepeatedItems() {
        Item bottle = ItemBuilder.newBuilder().name(BOTTLE_OF_PERFUME).price(10).build();
        Item book = ItemBuilder.newBuilder().name(BOOK).price(15).build();
        return Arrays.asList(bottle, book, book, bottle);
    }

    public static List<Item> getIncorrectInput() {
        return Arrays.asList(ItemBuilder.newBuilder().name(BOX_OF_CHOCOLATES).price(-10).build(),
                ItemBuilder.newBuilder().name(BOTTLE_OF_PERFUME).price(10).build());
    }
}
