package kata.supermarket;

import kata.supermarket.discounts.DiscountCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TotalCalculator {

    private final DiscountCalculator discountCalculator;

    public TotalCalculator(DiscountCalculator discountCalculator) {

        this.discountCalculator = discountCalculator;
    }
    private BigDecimal subtotal(List<Item> items) {
        return items.stream().map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * This could be a good place to apply the results of
     *  the discount calculations.
     *  It is not likely to be the best place to do those calculations.
     *  Think about how Basket could interact with something
     *  which provides that functionality.
     * @param items
     */
    private BigDecimal discounts(List<Item> items) {
        return discountCalculator.calculateDiscounts(items);
    }

    public BigDecimal calculate(List<Item> items) {
        return subtotal(items).subtract(discounts(items));
    }
}
