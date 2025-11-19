package user;

import payment.strategy.PaymentMethod;

/**
 * 사용자 정보를 담는 클래스
 */
public class User {
    private Long userId;
    private String password;
    private String username;
    private String email;
    private PaymentMethod paymentMethod; // 사용자가 주로 사용하는 결제 수단
    private UserGrade userGrade; // 사용자 등급
    private boolean isLoggedIn = false; // 로그인 중인지 확인

    // 생성자 (아이디, 패스워드, 사용자명, 이메일, 결제수단, 회원등급, 로그인확인)
    public User (Long userId, String password, String username, String email,
                 PaymentMethod paymentMethod, UserGrade userGrade, boolean isLoggedIn){
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.userGrade = userGrade;
        this.isLoggedIn = isLoggedIn;
    }

    // getter
    public Long getUserId() { return userId; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public UserGrade getUserGrade() { return userGrade; }
    public boolean isLoggedIn() { return isLoggedIn; }

    // setter

    public void setUserId(Long userId) { this.userId = userId; }
    public void setPassword(String password) { this.password = password; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setUserGrade(UserGrade userGrade) { this.userGrade = userGrade; }
    public void setIsLoggedIn(boolean isLoggedIn) { this.isLoggedIn = isLoggedIn; }
}