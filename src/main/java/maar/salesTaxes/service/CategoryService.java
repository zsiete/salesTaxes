package maar.salesTaxes.service;

import maar.salesTaxes.model.category.Category;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Service to manage the mapping between item descriptions and their category.
 */
public class CategoryService {
    private static final String BOOK = "book";
    private static final String CHOCOLATE = "chocolate";
    private static final String PILLS = "pills";

    /**
     * Map from descriptions to categories
     */
    private static final Map<String, Category> CATEGORY_MAP;

    /** Static initialization of the map */
    static {
        Map<String, Category> map = new HashMap<>();

        map.put(BOOK, Category.BOOK);
        map.put(CHOCOLATE, Category.FOOD);
        map.put(PILLS, Category.MEDICINE);

        CATEGORY_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * Returns the category corresponding to an item description.
     *
     * @param itemDescription The item description.
     * @return The corresponding category.
     */
    public Category getCategory(String itemDescription) {
        Category category;

        // An item belongs to a category if it contains a keyword
        if (itemDescription.contains(BOOK)) {
            category = Category.BOOK;
        } else if (itemDescription.contains(CHOCOLATE)) {
            category = Category.FOOD;
        } else if (itemDescription.contains(PILLS)) {
            category = Category.MEDICINE;
        } else {
            category = Category.OTHER;
        }

        return category;
    }
}
