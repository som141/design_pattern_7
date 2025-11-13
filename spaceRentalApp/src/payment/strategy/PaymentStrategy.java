package payment.strategy;

import payment.PaymentService;
import reservation.Reservation;

public interface PaymentStrategy {
        void pay(PaymentService ps, Reservation r);
}
