package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private final String identifier;

    ItemByUnit(String identifier, final Product product) {
        this.identifier = identifier;
        this.product = product;
    }

    @Override
    public String identifier() { return identifier; }

    public BigDecimal price() {
        return product.pricePerUnit();
    }
}
