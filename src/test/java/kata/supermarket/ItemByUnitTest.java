package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemByUnitTest {

    @DisplayName("identifier returned is same as item identifier")
    @ParameterizedTest(name = "{0}")
    @MethodSource
    void identifier(String description, Item item, String expectedIdentifier) {
        assertEquals(item.identifier(), expectedIdentifier);
    }

    static Stream<Arguments> identifier() {
        return Stream.of(
                Arguments.of("Item with identifier A", itemWithIdentifier("A"), "A"),
                Arguments.of("Item with identifier B",itemWithIdentifier("B"), "B"),
                Arguments.of("Item with identifier C",itemWithIdentifier("C"), "C"),
                Arguments.of("Item with identifier D",itemWithIdentifier("D"), "D")
        );
    }

    private static Item itemWithIdentifier(String a) {
        return new Product(a, ONE).oneOf();
    }
}