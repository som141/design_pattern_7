package space.option;

import java.math.BigDecimal;

/**
 * 공간의 종류를 나타내는 Enum
 */
public enum SpaceType { OFFICE(new BigDecimal(2000))
    , CAFE(new BigDecimal(3000)), STUDIO(new BigDecimal(4000)),
    STORAGE(new BigDecimal(5000));
    private final BigDecimal price;

    SpaceType(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
    /* 요구사항에 맞게 수정 */ }
