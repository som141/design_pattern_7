package user;

import java.math.BigDecimal;

/**
 * 사용자 등급을 나타내는 Enum
 */
public enum UserGrade {
    BRONZE(new BigDecimal(0.95)),
    SILVER(new BigDecimal(0.90)),
    GOLD(new BigDecimal(0.85));
    private final BigDecimal discount;

    UserGrade(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
