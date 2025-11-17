package payment.strategy;

import java.math.BigDecimal;

import payment.PaymentService;
import reservation.Reservation;

public class KakaoPayStrategy implements PaymentStrategy{
    public void pay(PaymentService ps, Reservation r) {
        BigDecimal price = ps.Total(r);
        System.out.println("카카오페이 " + price.multiply(new BigDecimal("0.97")).setScale(0, BigDecimal.ROUND_HALF_UP) + "원 결제 진행합니다. (3% 추가 할인)");
        System.out.println("카카오페이 결제 요청 중...");
        System.out.println("...");
        System.out.println("결제가 완료되었습니다.");
    }
}
