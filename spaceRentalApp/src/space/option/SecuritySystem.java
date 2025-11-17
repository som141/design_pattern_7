package space.option;

import java.math.BigDecimal;

/**
 * 보안 시스템의 종류를 나타내는 Enum
 */
public enum SecuritySystem { DOOR_LOCK(new BigDecimal(300)),
                             CCTV(new BigDecimal(400)),
                            ACCESS_CARD(new BigDecimal(500));
    private final BigDecimal pirce;/* 수정 */

    SecuritySystem(BigDecimal pirce) {
        this.pirce = pirce;
    }

    public BigDecimal getPirce() {
        return pirce;
    }
}
