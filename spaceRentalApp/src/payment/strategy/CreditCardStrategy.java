package payment.strategy;

import java.math.BigDecimal;

import payment.PaymentService;
import reservation.Reservation;

public class CreditCardStrategy implements PaymentStrategy{
    public void pay(PaymentService ps, Reservation r) {
        BigDecimal price = ps.Total(r);
        System.out.println("등록된 신용카드로 " + price.multiply(new BigDecimal("0.98")) + "원 결제 진행합니다. (2% 추가 할인)");
        System.out.println("...");
        System.out.println("결제가 완료되었습니다.");
    }
}
