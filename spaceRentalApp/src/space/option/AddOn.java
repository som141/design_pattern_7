package space.option;

import java.math.BigDecimal;

public enum AddOn {
    WIFI(new BigDecimal(1400)),
    PROJECTOR(new BigDecimal(1500));
    private final BigDecimal value;
    private AddOn(BigDecimal value) {
        this.value = value;
    }
    public BigDecimal getValue() {
        return value;
    }
}
