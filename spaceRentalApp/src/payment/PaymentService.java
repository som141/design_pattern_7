package payment;

import payment.discount.DiscountPolicy;
import payment.discount.DiscountPolicyFactory;
import payment.securitysystempayment.SecurityPaymentFactory;
import payment.spacescale.ScalePaymentFactory;
import payment.spacetype.SpaceTypePaymentFactory;
import payment.unitspace.UnitSpacePaymentFactory;
import reservation.Reservation;
import space.Space;
import space.SpaceRepository;
import user.MemoryUserRepository;
import user.User;
import user.UserGrade;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentService {
    private final SpaceRepository spaceRepository;
    private final MemoryUserRepository userRepository;
    public PaymentService(SpaceRepository spaceRepository,
                          MemoryUserRepository userRepository) {
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;

    }

    public BigDecimal previewOnedayTotal(Space space) {
        SecurityPaymentFactory spf = new SecurityPaymentFactory();
        ScalePaymentFactory cpf = new ScalePaymentFactory();
        SpaceTypePaymentFactory stpf = new SpaceTypePaymentFactory();
        BigDecimal result =new BigDecimal(0);
        result = stpf.get(space.getType()).apply(cpf.get(space.getScale()).apply(space.getSecurities().stream().map(e->spf.get(e).applyPayment(new BigDecimal(0))).reduce(BigDecimal::add).get()));
        return result;
    }


    public BigDecimal OnedayTotal(Reservation reservation) {
        BigDecimal result =new BigDecimal(0);
        Space space=spaceRepository.findById(reservation.getSpaceId()).orElseThrow(()->new IllegalArgumentException("해당 장소가 없음.."));
        BigDecimal base = previewOnedayTotal(space);
        User user = userRepository.findById(reservation.getUserId());
        UnitSpacePaymentFactory usp = new UnitSpacePaymentFactory();
        DiscountPolicyFactory dpf = new DiscountPolicyFactory();
        result =dpf.get(user.getUserGrade()).discount(base.add(space.getUnits().stream().map(e->usp.get(e).apply(new BigDecimal(0))).reduce(BigDecimal::add).get()));

        return result;
    }
    public BigDecimal Total(Reservation reservation){
        int period=reservation.getTime().getPeriod();// 여기 time State메서드로 수정해줘야함.
        BigDecimal base =OnedayTotal(reservation);
        return base.multiply(new BigDecimal(period));
    }
}
