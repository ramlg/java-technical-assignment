package kata.supermarket;

import kata.supermarket.discounts.DiscountCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TotalCalculatorTest {

    @Mock
    private DiscountCalculator discountCalculator;

    private TotalCalculator totalCalculator;

    @BeforeEach
    public void beforeEachTest() {
        openMocks(this);
        totalCalculator = new TotalCalculator(discountCalculator);
        when(discountCalculator.calculateDiscounts(anyList())).thenReturn(ZERO);
    }

    @DisplayName("totalCalculator provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void calculatorTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        List<Item> itemsList = stream(items.spliterator(), false)
                .collect(toList());
        assertEquals(new BigDecimal(expectedTotal),
                totalCalculator.calculate(itemsList));
    }

    static Stream<Arguments> calculatorTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    @DisplayName("Discount applied from discount calculator")
    @Test
    public void discountFromDiscountCalculatorIsAppliedOnTotalAmount() {

        List<Item> items = itemsWithDiscounts();
        when(discountCalculator.calculateDiscounts(items)).thenReturn(new BigDecimal("0.20"));
        assertEquals(new BigDecimal("2.82"), totalCalculator.calculate(items));

    }

    private List<Item> itemsWithDiscounts() {
        return Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk(), aPackOfDigestives());
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product("pintOfMilk", new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product("packOfDigestive", new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct("kiloOfAmericanSweets", new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct("aKiloOfPickAndMix", new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

}