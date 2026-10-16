package utils;

import junit.framework.TestCase;

public class DistanceCalculationTest extends TestCase {
    public void testDistanceUsesCoordinateDifferencesInBothDirections() {
        assertEquals(5.0, DistanceCalculation.geoCalculation(1, 2, 4, 6), 1e-10);
        assertEquals(5.0, DistanceCalculation.geoCalculation(4, 6, 1, 2), 1e-10);
        assertEquals(0.0, DistanceCalculation.geoCalculation(4, 6, 4, 6), 1e-10);
    }
}
