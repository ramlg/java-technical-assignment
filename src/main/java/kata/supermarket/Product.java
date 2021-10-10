package kata.supermarket;

import java.math.BigDecimal;

public class Product {


    private final BigDecimal pricePerUnit;
    private final String identifier;

    public Product(String identifier, final BigDecimal pricePerUnit) {
        this.identifier = identifier;
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(identifier, this);
    }
}
