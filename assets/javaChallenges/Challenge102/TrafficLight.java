package Challenge102;

public class TrafficLight extends Thread {
    private final TrafficColor color;

    public TrafficLight(TrafficColor color) {
        this.color = color;
    }

    @Override
    public  void run() {
        synchronized (TrafficLight.class) {
            System.out.println("Active color:" + color);
            try {
                Thread.sleep(color.getTime());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inactive color:" + color);
        }
    }
}
