import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Challenge103 {
    static void main() {
        ExecutorService exe= Executors.newSingleThreadExecutor();
        Runnable Task=()->{
            int i=0;
            for(i=0;i<10;i++)
                System.out.println(i+1);
        };
        exe.submit(Task);
        exe.shutdown();
    }

}
