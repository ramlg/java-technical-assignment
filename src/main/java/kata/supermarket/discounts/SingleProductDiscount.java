package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public class SingleProductDiscount implements Discount {

    private final String discountName;
    private final int numberOfItemsInGroup;
    private final BigDecimal discount;

    public SingleProductDiscount(String discountName, int numberOfItemsInGroup, BigDecimal discount) {
        this.discountName = discountName;
        this.numberOfItemsInGroup = numberOfItemsInGroup;
        this.discount = discount;
    }

    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        int numberOfDiscounts = items.size()/numberOfItemsInGroup;
        return discount.multiply(new BigDecimal(numberOfDiscounts));
    }
}
