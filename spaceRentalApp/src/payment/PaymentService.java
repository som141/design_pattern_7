package payment;


import payment.strategy.PaymentStrategy;
import reservation.Reservation;
import space.domain.Space;
import space.SpaceRepository;
import space.option.AddOn;
import space.option.SecuritySystem;
import space.option.UnitSpace;
import user.MemoryUserRepository;
import user.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentService {
    private final SpaceRepository spaceRepository=new SpaceRepository();
    private final MemoryUserRepository userRepository= new MemoryUserRepository();

    public BigDecimal previewOnedayTotal(Space space) {

        BigDecimal result =new BigDecimal(0);
        result = space.getScale().getPrice().add(space.getType().getPrice().add(
                space.getUnits().stream().map(UnitSpace::getPrice).reduce( BigDecimal::add).get().add(
                        space.getSecurities().stream().map(SecuritySystem::getPirce).reduce( BigDecimal::add).get()
                )
        ));
        return result;
    }


    public BigDecimal OnedayTotal(Reservation reservation) {
        Space space=spaceRepository.findById(reservation.getSpaceId()).orElseThrow(()->new IllegalArgumentException("해당 장소가 없음.."));
        BigDecimal base = previewOnedayTotal(space);
        User user = userRepository.findById(reservation.getUserId());
        BigDecimal result = user.getUserGrade().getDiscount().multiply(base.add(space.getAddOn().stream().map(AddOn::getValue).reduce(BigDecimal::add).get())).setScale(0,RoundingMode.HALF_UP);

        return result;
    }
    public BigDecimal Total(Reservation reservation){
        int period=reservation.getTime().getPeriod();// 여기 time State메서드로 수정해줘야함.
        BigDecimal base =OnedayTotal(reservation);
        return base.multiply(new BigDecimal(period));
    }
    public void pay(Reservation reservation) {
        User user = userRepository.findById(reservation.getUserId());
        PaymentStrategy strategy = user.getPaymentMethod().getStrategy();
        strategy.pay(this, reservation);
    }

}
