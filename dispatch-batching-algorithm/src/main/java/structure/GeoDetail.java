package structure;

public class GeoDetail {
    double longitude;
    double latitude;

    public GeoDetail(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GeoDetail() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
