public class Challenge101 extends Thread {
    public void run(){
        for(int i=0;i<10;i++)
            System.out.println("Hello from "+Thread.currentThread().getName());
    }

    static void main(String[] args) {
        Challenge101 t1=new Challenge101();
        Challenge101 t2=new Challenge101();
        Challenge101 t3=new Challenge101();
        t3.setName("Thread 3");
        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t3.start();





    }
}
