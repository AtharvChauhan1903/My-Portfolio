package Challenge102;

import static Challenge102.TrafficColor.*;

public class Execution {
    static void main() throws InterruptedException {
        TrafficLight t1=new TrafficLight(Red);
        TrafficLight t2=new TrafficLight(Yellow);
        TrafficLight t3=new TrafficLight(Green);
        t1.start();

        t2.start();

        t3.start();
    }
}
