import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Challenge104 {

    static void main() {
        ExecutorService exe= Executors.newFixedThreadPool(3);

        Runnable rr=()->{
            System.out.println("Hello from "+Thread.currentThread());
            try {
                Random r = new Random();
                int time = r.nextInt(5) + 1;
                Thread.sleep(time * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        Runnable tt=()->{
            System.out.println("Hello from "+Thread.currentThread());
            try {
                Random r = new Random();
                int time = r.nextInt(5) + 1;
                Thread.sleep(time * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable pp=()->{
            System.out.println("Hello from "+Thread.currentThread());
            try {
                Random r = new Random();
                int time = r.nextInt(5) + 1;
                Thread.sleep(time * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        exe.submit(rr);

        exe.submit(tt);

        exe.submit(pp);
        exe.shutdown();
        try {
            Random r=new Random();
            int time=r.nextInt(5)+1;
            exe.awaitTermination(time, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
