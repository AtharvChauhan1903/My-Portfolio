package Challenge102;

public enum TrafficColor {
    Red(9000),
    Yellow(9000),
    Green(10000);
    private final int time;

    TrafficColor(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
