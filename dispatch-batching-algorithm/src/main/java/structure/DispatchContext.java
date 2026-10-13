package structure;

public class DispatchContext {
    private final int currentTime;
    private final int weather;
    private final int rainFall;

    public DispatchContext(int currentTime, int weather, int rainFall) {
        this.currentTime = currentTime;
        this.weather = weather;
        this.rainFall = rainFall;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getWeather() {
        return weather;
    }

    public int getRainFall() {
        return rainFall;
    }
}
