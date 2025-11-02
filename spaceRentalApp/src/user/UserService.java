package user;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스
 */
public class UserService {
    private final MemoryUserRepository repository = new MemoryUserRepository();

    public User login(Long id, String password) {     // 로그인 로직
        User user = repository.findById(id);
        if (user != null && password.equals(user.getPassword())) {      // 패스워드 일치 확인
            user.setIsLoggedIn(true);       // 로그인 중
            repository.updateById(id, user);    // 변경 사항 반영
            System.out.println(user.getUsername() + " 님 로그인되었습니다.");
            return user;
        }
        System.out.println("로그인에 실패했습니다.");
        return null;
    }

    public void register(User user) {       // 회원 가입 로직
        if (repository.findById(user.getUserId()) != null) {        // 해당 id가 이미 사용 중인 경우
            System.out.println("이미 존재하는 사용자입니다.");
        }
        else {
            repository.save(user);
            System.out.println("가입되었습니다.");
        }
    }
    public void logout(User user) {         // 로그 아웃 로직
        if (!user.isLoggedIn()) return;      // 로그인 필요
        user.setIsLoggedIn(false);       // 로그 아웃
        repository.updateById(user.getUserId(), user);    // 변경 사항 반영
        System.out.println("로그아웃되었습니다.");
    }
    public void editInformation(User user) {        // 회원 정보 수정 로직
        if (!user.isLoggedIn()) return;      // 로그인 필요
        if (repository.updateById(user.getUserId(),user)) {     // 정상적으로 수정된 경우
            System.out.println("회원 정보 수정 완료");
        }
        else System.out.println("해당 사용자가 없어 수정이 불가능 합니다.");
    }
    public void removeUser(User user) {    // 회원 탈퇴 로직
        if (!user.isLoggedIn()) return;      // 로그인 필요
        repository.deleteById(user.getUserId());
        System.out.println(user.getUsername() + " 님 탈퇴되었습니다.");
    }
    public String userInformation(User user) {      // 회원 정보 조회 로직 ()
        if (!user.isLoggedIn()) return "로그인이 필요합니다.";
        User realUser = repository.findById(user.getUserId());
        if (realUser != null) {     // 존재하는 회원의 경우
            return user.getUsername() + " 님의 회원 정보입니다."
                    + "\n-id: " + user.getUserId()
                    + "\n-email: " + user.getEmail()
                    + "\n-결제 수단: " + user.getPaymentMethod()
                    + "\n-회원 등급: " + user.getUserGrade();
        }
        return "사용자 정보 없음";
    }
}
