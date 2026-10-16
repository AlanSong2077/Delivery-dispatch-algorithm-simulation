package processing;

import junit.framework.TestCase;
import structure.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MatchProcessingTest extends TestCase {
    public void testInitializesPathAndOrderContext() throws Exception {
        CourierDetail courier = courier();
        courier.setOrderDetailList(null);
        OrderDetail order = order(2, 1);
        assign(courier, order);
        assertPath(courier, order.getStartPointGeo(), order.getEndPointGeo());
        assertSame(order.getStartPointGeo(), courier.getNextPoint());
        assertEquals("rider", order.getCourierId());
        assertEquals(2, courier.getAvailableCapacity());
        assertTrue(courier.getOrderDetailList().getOrderDetailList().contains(order));
    }

    public void testInsertsBetweenExistingStopsWithoutReorderingThem() throws Exception {
        CourierDetail courier = courier();
        GeoDetail first = new GeoDetail(1, 0);
        GeoDetail last = new GeoDetail(4, 0);
        setPath(courier, first, last);
        OrderDetail order = order(2, 3);
        assign(courier, order);
        assertPath(courier, first, order.getStartPointGeo(), order.getEndPointGeo(), last);
        assertSame(first, courier.getNextPoint());
    }

    public void testInsertsBeforeFirstStopAndUpdatesNextPoint() throws Exception {
        CourierDetail courier = courier();
        GeoDetail last = new GeoDetail(4, 0);
        setPath(courier, last);
        OrderDetail order = order(1, 2);
        assign(courier, order);
        assertPath(courier, order.getStartPointGeo(), order.getEndPointGeo(), last);
        assertSame(order.getStartPointGeo(), courier.getNextPoint());
    }

    public void testInsertsAllOrdersAndKeepsPickupBeforeDelivery() throws Exception {
        CourierDetail courier = courier();
        OrderDetail first = order(2, 1);
        OrderDetail second = order(3, 4);
        assign(courier, first, second);
        ArrayList<GeoDetail> path = path(courier).getGeoDetails();
        assertEquals(4, path.size());
        for (OrderDetail order : Arrays.asList(first, second)) {
            assertTrue(path.indexOf(order.getStartPointGeo()) >= 0);
            assertTrue(path.indexOf(order.getStartPointGeo()) < path.indexOf(order.getEndPointGeo()));
        }
        assertEquals(1, courier.getAvailableCapacity());
    }

    public void testCanAppendBothNodesAtEnd() throws Exception {
        CourierDetail courier = courier();
        GeoDetail first = new GeoDetail(1, 0);
        setPath(courier, first);
        OrderDetail order = order(2, 3);
        assign(courier, order);
        assertPath(courier, first, order.getStartPointGeo(), order.getEndPointGeo());
    }

    public void testInvalidOrderDoesNotPartiallyUpdateCourier() throws Exception {
        CourierDetail courier = courier();
        GeoDetail existing = new GeoDetail(1, 0);
        setPath(courier, existing);
        OrderDetail valid = order(2, 3);
        OrderDetail invalid = order(4, 5);
        invalid.setEndPointGeo(null);
        try {
            assign(courier, valid, invalid);
            fail("Missing delivery point must be rejected");
        } catch (java.lang.reflect.InvocationTargetException exception) {
            assertTrue(exception.getCause() instanceof IllegalArgumentException);
        }
        assertPath(courier, existing);
        assertEquals(3, courier.getAvailableCapacity());
        assertTrue(courier.getOrderDetailList().getOrderDetailList().isEmpty());
        assertNull(valid.getCourierId());
        assertNull(invalid.getCourierId());
    }

    private CourierDetail courier() {
        CourierDetail courier = new CourierDetail(true, new GeoDetail(0, 0));
        courier.setCourierId("rider");
        courier.setAvailableCapacity(3);
        courier.setOrderDetailList(new OrderDetailList());
        return courier;
    }

    private OrderDetail order(double pickup, double delivery) {
        OrderDetail order = new OrderDetail();
        order.setStartPointGeo(new GeoDetail(pickup, 0));
        order.setEndPointGeo(new GeoDetail(delivery, 0));
        return order;
    }

    private void assign(CourierDetail courier, OrderDetail... orders) throws Exception {
        HashMap<CourierDetail, OrderDetailList> matches = new HashMap<>();
        matches.put(courier, new OrderDetailList(new ArrayList<>(Arrays.asList(orders))));
        Method update = MatchProcessing.class.getDeclaredMethod("UpdateCourierOrderContext", HashMap.class);
        update.setAccessible(true);
        update.invoke(null, matches);
    }

    private GeoDetailList path(CourierDetail courier) throws Exception {
        return courier.getDeliveryPath();
    }

    private void setPath(CourierDetail courier, GeoDetail... stops) throws Exception {
        courier.setDeliveryPath(new GeoDetailList(new ArrayList<>(Arrays.asList(stops))));
    }

    private void assertPath(CourierDetail courier, GeoDetail... stops) throws Exception {
        assertNotNull(path(courier));
        assertEquals(Arrays.asList(stops), path(courier).getGeoDetails());
    }
}
