package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleProductDiscountTest {


    @DisplayName("Single product discount will return the discount for ")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void calculatorDiscount(String description, String expectedDiscount, Iterable<Item> items) {
        SingleProductDiscount singleProductDiscount = new SingleProductDiscount("Three for £1.20", 3, new BigDecimal("0.30"));

        List<Item> itemsList = stream(items.spliterator(), false)
                .collect(toList());
        assertEquals(new BigDecimal(expectedDiscount),
                singleProductDiscount.calculateDiscount(itemsList));
    }

    static Stream<Arguments> calculatorDiscount() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                threeItems(),
                fourItems(),
                sixItems()
        );
    }


    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments threeItems() {
        return Arguments.of("three items", "0.30", Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments fourItems() {
        return Arguments.of("four items", "0.30", Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }
    private static Arguments sixItems() {
        return Arguments.of("six items", "0.60", Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk(),
                aPintOfMilk(), aPintOfMilk(),aPintOfMilk()));
    }

    private static Item aPintOfMilk() {
        return new Product("pintOfMilk", new BigDecimal("0.50")).oneOf();
    }

}