package filter;

import EnumType.CourierType;
import org.springframework.stereotype.Component;
import structure.CourierDetail;
import structure.CourierOrderScore;
import structure.OrderDetail;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeFilter implements Filter{
    private final int THRESHOLD_PRICE = 3000;

    @Override
    public boolean compute(CourierDetail courier, ArrayList<OrderDetail> orders) {
        // 包一些业务逻辑：高价值商品只能给自营骑手送
        CourierType courierType = courier.getCourierType();

        boolean isPass = true;
        for (OrderDetail order : orders) {
            if(order.getPrice() >= THRESHOLD_PRICE && !courierType.equals(CourierType.valueOf("SelfOperated"))){
                isPass = false;
            }
        }
        return isPass;







    }
}
