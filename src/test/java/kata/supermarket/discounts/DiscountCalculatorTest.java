package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;

    @BeforeEach
    public void beforeEachTest() {
        Map<String, Discount> discounts = new HashMap<>();
        discounts.put("pintOfMilk", new SingleProductDiscount("Three for the price of two", 3, new BigDecimal("0.30")));
        discounts.put("digestiveBiscuit", new SingleProductDiscount("Two for £1", 2, new BigDecimal("0.20")));
        discountCalculator = new DiscountCalculator(discounts);
    }
    @DisplayName("Total discount will return the discount for ")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void calculatorAllDiscounts(String description, String expectedDiscount, Iterable<Item> items) {
        List<Item> itemsList = stream(items.spliterator(), false)
                .collect(toList());
        assertEquals(new BigDecimal(expectedDiscount),
                discountCalculator.calculateDiscounts(itemsList));
    }

    static Stream<Arguments> calculatorAllDiscounts() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                threePintOfMilk(),
                threePintOfMilkAndADigestiveBiscuit(),
                threePintOfMilkAndThreeDigestiveBiscuit(),
                sixPintOfMilkAndThreeDigestiveBiscuit()

        );
    }


    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a pint of milk", "0.00", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments threePintOfMilk() {
        return Arguments.of("three pints of milk", "0.30",
                asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments threePintOfMilkAndADigestiveBiscuit() {
        return Arguments.of("three pints of milk and one digestive biscuit", "0.30",
                asList(aPintOfMilk(), aPintOfMilk(),
                        aDigestiveBiscuit(), aPintOfMilk()));
    }

    private static Arguments threePintOfMilkAndThreeDigestiveBiscuit() {
        return Arguments.of("three pints of milk and three digestive biscuit", "0.50",
                asList(aPintOfMilk(), aPintOfMilk(),
                        aDigestiveBiscuit(), aDigestiveBiscuit(),
                        aPintOfMilk(), aDigestiveBiscuit()));
    }

    private static Arguments sixPintOfMilkAndThreeDigestiveBiscuit() {
        return Arguments.of("six pints of milk and three digestive biscuit", "0.80",
                asList(aPintOfMilk(), aPintOfMilk(),
                        aDigestiveBiscuit(), aDigestiveBiscuit(),
                        aPintOfMilk(), aDigestiveBiscuit(), aPintOfMilk(),
                        aPintOfMilk(), aPintOfMilk()));
    }

    private static Item aPintOfMilk() {
        return new Product("pintOfMilk", new BigDecimal("0.50")).oneOf();
    }
    private static Item aDigestiveBiscuit() { return new Product("digestiveBiscuit", new BigDecimal("0.60")).oneOf(); }
}