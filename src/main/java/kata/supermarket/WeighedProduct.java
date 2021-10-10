package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final String identifier;

    public WeighedProduct(String identifier, final BigDecimal pricePerKilo) {
        this.identifier = identifier;
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(identifier, this, kilos);
    }
}
