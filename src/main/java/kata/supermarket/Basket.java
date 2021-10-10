package kata.supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final TotalCalculator totalCalculator;
    private final List<Item> items;

    public Basket(TotalCalculator totalCalculator) {
        this.totalCalculator = totalCalculator;
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return totalCalculator.calculate(items());
    }
}
