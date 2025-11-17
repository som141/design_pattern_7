package space.option;

import java.math.BigDecimal;

/**
 * 공간의 규모를 나타내는 Enum
 */
public enum SpaceScale { LARGE(new BigDecimal(50000)),
    MEDIUM(new BigDecimal(30000)),
    SMALL(new BigDecimal(20000)),
    ONE_PERSON(new BigDecimal(10000)); /* 필요시 수정 */

    private final BigDecimal price;
    SpaceScale(BigDecimal bigDecimal) {
        this.price = bigDecimal;
    }

    public BigDecimal getPrice() {
        return price;
    }
}