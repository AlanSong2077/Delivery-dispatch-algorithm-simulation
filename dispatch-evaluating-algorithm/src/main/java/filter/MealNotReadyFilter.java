package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import structure.CourierDetail;
import structure.CourierOrderScore;
import structure.DispatchContext;
import structure.OrderDetail;

import java.util.ArrayList;
import java.util.List;

@Component
public class MealNotReadyFilter implements Filter{
    // 如果餐没好 直接过滤 这里设置一个阈值
    private final int timeThreshold = 60 * 10;

    @Autowired
    private DispatchContext dispatchContext;

    @Override
    public boolean compute(CourierDetail courier, ArrayList<OrderDetail> orders) {
        // 判读当前时间和出餐时间差异
        for (OrderDetail order : orders) {
            if(order.getMealPredictReadyTime() - dispatchContext.getCurrentTime() < timeThreshold){
                return true;
            }
        }
        return false;
    }
}
