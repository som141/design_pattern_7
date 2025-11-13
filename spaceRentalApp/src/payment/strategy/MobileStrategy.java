package payment.strategy;

import java.math.BigDecimal;

import payment.PaymentService;
import reservation.Reservation;

public class MobileStrategy implements PaymentStrategy{
    public void pay(PaymentService ps, Reservation r) {
        BigDecimal price = ps.Total(r);
        System.out.println("휴대폰 결제로 " + price + "원 결제 진행합니다. (익월 통신요금과 합산됩니다.)");
        System.out.println("...");
        System.out.println("결제가 완료되었습니다.");
    }
}
