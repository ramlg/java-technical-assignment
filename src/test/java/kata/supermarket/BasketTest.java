package kata.supermarket;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class BasketTest {

    @Mock
    private TotalCalculator totalCalculator;

    @Before
    public void beforeEachTest() {
        openMocks(this);
    }

    @DisplayName("Basket will return total calculated using totalCalculator")
    @Test
    public void basketWillReturnTotalCalculatedFromTotalCalculator() {

        Item item = new Product("identifier", new BigDecimal(2)).oneOf();
        final Basket basket = new Basket(totalCalculator);
        basket.add(item);
        when(totalCalculator.calculate(singletonList(item)))
                .thenReturn(new BigDecimal(2));
        assertEquals(new BigDecimal(2), basket.total());
    }
}