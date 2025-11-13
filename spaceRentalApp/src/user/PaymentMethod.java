package user;

import payment.strategy.*;

/**
 * 결제 수단 종류를 나타내는 Enum
 */
public enum PaymentMethod {
    CREDIT_CARD("신용카드", new CreditCardStrategy()),    // 신용 카드
    MOBILE("휴대폰 결제", new MobileStrategy()),     // 모바일 결제 (휴대폰 결제)
    BANK_TRANSFER("무통장입금", new BankTransferStrategy()),     // 무통장 입금
    KAKAO_PAY("카카오페이", new KakaoPayStrategy());   // 카카오 페이

    private final String name;
    private final PaymentStrategy strategy;

    PaymentMethod(String name, PaymentStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }
}
