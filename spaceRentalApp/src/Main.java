import space.domain.DefaultSpaceFactory;
import space.domain.SpaceFactory;
import payment.PaymentService;
import reservation.Reservation;
import reservation.ReservationService;
import reservation.TimeState;
import space.*;
import space.domain.Space;
import space.option.*;
import user.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MemoryUserRepository userRepository = new MemoryUserRepository();
        SpaceService spaceService = new SpaceService();

        UserService userService = new UserService();
        SpaceFactory spaceFactory = new DefaultSpaceFactory();
        ReservationService reservationService = new ReservationService();
        // PaymentService 생성자에 맞게 3개 인자 전달
        PaymentService paymentService = new PaymentService();


        // testUser 생성
        User testUser = new User(123L, "456", "형균", "123@", PaymentMethod.MOBILE, UserGrade.GOLD, false);
        userService.register(testUser);
        User loggedInUser = userService.login(123L, "456");

        // space 생성 (관리자가 생성)
        Space testSpace = spaceFactory.createSpace(
                1L, // Long ID
                "강남 3호점",
                SpaceType.OFFICE,
                SpaceScale.MEDIUM,
                List.of(UnitSpace.MEETING_ROOM, UnitSpace.LOUNGE),
                List.of(SecuritySystem.CCTV, SecuritySystem.DOOR_LOCK)
        );
        spaceService.createSpace(testSpace);
        System.out.println(testSpace.getPrice());
        // user와 space 참조해서 reservation 객체 생성
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0);
        LocalDateTime endTime = startTime.plusDays(10); // 1일 뒤
        TimeState timeState = new TimeState(startTime, endTime);
        testSpace.SetAddOn(List.of(AddOn.WIFI,AddOn.PROJECTOR));
        // [오류 수정 가정 1] ReservationService.reserve()가 Long spaceId를 받는다고 가정
        Reservation reservation = reservationService.reserve(
                loggedInUser.getUserId(),
                testSpace.getId(), // 1L (Long)
                startTime,
                endTime
        );
        
        // 결제 수단 테스트용 (CREDIT_CARD)
        User testUser2 = new User(124L, "456", "형균", "123@", PaymentMethod.CREDIT_CARD, UserGrade.GOLD, true);
        userService.register(testUser2);
        Space testSpace2 = spaceFactory.createSpace(2L, "강남 4호점", SpaceType.OFFICE, SpaceScale.MEDIUM,
                List.of(UnitSpace.MEETING_ROOM, UnitSpace.LOUNGE), List.of(SecuritySystem.CCTV, SecuritySystem.DOOR_LOCK));
        spaceService.createSpace(testSpace2);
        testSpace2.SetAddOn(List.of(AddOn.WIFI,AddOn.PROJECTOR));
        Reservation reservation2 = reservationService.reserve(testUser2.getUserId(), testSpace2.getId(), startTime, endTime);
        // 결제 수단 테스트용 (BANK_TRANSFER)
        User testUser3 = new User(125L, "456", "형균", "123@", PaymentMethod.BANK_TRANSFER, UserGrade.GOLD, true);
        userService.register(testUser3);
        Space testSpace3 = spaceFactory.createSpace(3L, "강남 5호점", SpaceType.OFFICE, SpaceScale.MEDIUM,
                List.of(UnitSpace.MEETING_ROOM, UnitSpace.LOUNGE), List.of(SecuritySystem.CCTV, SecuritySystem.DOOR_LOCK));
        spaceService.createSpace(testSpace3);
        testSpace3.SetAddOn(List.of(AddOn.WIFI,AddOn.PROJECTOR));
        Reservation reservation3 = reservationService.reserve(testUser3.getUserId(), testSpace3.getId(), startTime, endTime);
        // 결제 수단 테스트용 (KAKAO_PAY)
        User testUser4 = new User(126L, "456", "형균", "123@", PaymentMethod.KAKAO_PAY, UserGrade.GOLD, true);
        userService.register(testUser4);
        Space testSpace4 = spaceFactory.createSpace(4L, "강남 6호점", SpaceType.OFFICE, SpaceScale.MEDIUM,
                List.of(UnitSpace.MEETING_ROOM, UnitSpace.LOUNGE), List.of(SecuritySystem.CCTV, SecuritySystem.DOOR_LOCK));
        spaceService.createSpace(testSpace4);
        testSpace4.SetAddOn(List.of(AddOn.WIFI,AddOn.PROJECTOR));
        Reservation reservation4 = reservationService.reserve(testUser4.getUserId(), testSpace4.getId(), startTime, endTime);


        // reservation 객체를 payment에 넘겨서 가격 책정
        // (1) 할인 전 1일 기본가 (UnitSpace + SpaceType + Scale + Security)
        BigDecimal previewOneDay = paymentService.previewOnedayTotal(testSpace);
        System.out.println("1일 기본가 (할인 전): " + previewOneDay + "원");

        // (2) 할인 후 1일 최종가 (기본가에 GOLD 등급 할인 적용)
        BigDecimal finalOneDay = paymentService.OnedayTotal(reservation);
        System.out.println("1일 최종가 (GOLD 할인): " + finalOneDay + "원");

        // (3) 총 예약 기간 최종가 (PaymentService의 마지막 previewTotal 메서드 사용)
        BigDecimal finalTotalPrice = paymentService.Total(reservation);
        System.out.println(finalTotalPrice);

        // 결제 수단별 출력 비교
        System.out.println("\n-----휴대폰 결제-----");
        paymentService.pay(reservation);
        System.out.println("-----신용카드-----");
        paymentService.pay(reservation2);
        System.out.println("-----무통장입금-----");
        paymentService.pay(reservation3);
        System.out.println("-----카카오페이-----");
        paymentService.pay(reservation4);
    }
}