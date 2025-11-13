package payment.strategy;

import java.math.BigDecimal;

import payment.PaymentService;
import reservation.Reservation;

public class BankTransferStrategy implements PaymentStrategy{
    public void pay(PaymentService ps, Reservation r) {
        BigDecimal price = ps.Total(r);
        BigDecimal finalPrice = price.multiply(new BigDecimal("0.99"));
        System.out.println("무통장입금 " + finalPrice + "원 결제 진행합니다. (1% 추가 할인)");
        System.out.println("단국은행 123456789 (예금주명 : 공오)으로 " + finalPrice + "원을 24시간 이내에 입금해주세요.");
        System.out.println("...");
        System.out.println("결제가 완료되었습니다.");
    }
}