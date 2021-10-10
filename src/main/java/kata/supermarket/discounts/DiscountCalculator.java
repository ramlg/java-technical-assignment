package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.stream.Collectors.toList;

public class DiscountCalculator {

    private final Map<String, Discount> discounts;

    public DiscountCalculator(Map<String, Discount> discounts) {
        this.discounts = discounts;
    }

    public BigDecimal calculateDiscounts(List<Item> items) {
        BigDecimal totalDiscount = ZERO;
        for(Map.Entry<String, Discount> entry : discounts.entrySet()) {
            totalDiscount = totalDiscount.add(entry.getValue().calculateDiscount(getAllItemsForIdentifier(entry.getKey(), items)));
        }
        return totalDiscount.setScale(2, HALF_UP);
    }

    private List<Item> getAllItemsForIdentifier(String identifier, List<Item> items) {
       return items.stream()
                .filter(item -> item.identifier().equals(identifier))
                .collect(toList());
    }
}
