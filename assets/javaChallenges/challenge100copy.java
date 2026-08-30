public class challenge100copy {
    static void main() throws InterruptedException {
        challenge100 t1=new challenge100();
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
