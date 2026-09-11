
import java.util.Scanner;
import java.util.concurrent.*;

public class Challenge105 {
    static void main() {
        ExecutorService exe= Executors.newSingleThreadExecutor();
        Scanner input=new Scanner(System.in);
        Callable <Integer>cc=()->{
            System.out.println("Enter the value you want to add");
            int y= input.nextInt();
            System.out.println("Enter the value you want to add");
            int s= input.nextInt();
            return s+y;
        };
        Future<Integer>result= exe.submit(cc);
        try {
            Integer answer = result.get();
        System.out.println(answer);
        }catch (InterruptedException e){
            throw new RuntimeException();
        }catch (ExecutionException e){
            throw new RuntimeException();
        }

        exe.shutdown();
    }
}
