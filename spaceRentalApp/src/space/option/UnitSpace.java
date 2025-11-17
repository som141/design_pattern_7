package space.option;

import java.math.BigDecimal;

/**
 * 공간 내 세부 단위 공간의 종류를 나타내는 Enum
 */

public enum UnitSpace { MEETING_ROOM(new BigDecimal(1500)),
    SEMINAR_ROOM(new BigDecimal(1600)), STUDIO(new BigDecimal(1700)),
    LOUNGE(new BigDecimal(1800)), BREAK_ROOM(new BigDecimal(1900)),
    PRIVATE_DESK(new BigDecimal(2000)), OPEN_SEAT(new BigDecimal(2100));
        private final BigDecimal price;

    UnitSpace(BigDecimal value) {
        this.price = value;
    }

    public BigDecimal getPrice() {
        return price;
    }
}