public class ThreadStateDemo {
    static void main() throws InterruptedException {
        ThreadState t1=new ThreadState();
        System.out.println(t1.getState());
        t1.setName("Thread 1");

        t1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.join();

        System.out.println("After completion "+t1.getState());

    }

}
