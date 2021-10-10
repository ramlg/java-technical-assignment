package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;
    private final String identifier;

    ItemByWeight(String identifier, final WeighedProduct product, final BigDecimal weightInKilos) {
        this.identifier = identifier;
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    @Override
    public String identifier() {
        return identifier;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
