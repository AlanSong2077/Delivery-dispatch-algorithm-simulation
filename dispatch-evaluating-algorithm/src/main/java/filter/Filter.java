package filter;

import structure.CourierDetail;
import structure.CourierOrderScore;
import structure.OrderDetail;

import java.util.ArrayList;

public interface Filter {
    boolean compute(CourierDetail courier, ArrayList<OrderDetail> orders);
}
