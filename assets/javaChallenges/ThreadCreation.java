public class ThreadCreation extends Thread{
   public void run(){
       for(int i=0;i<10;i++)
           System.out.println("Hello from "+Thread.currentThread().getName());
   }

    static void main() {
        ThreadCreation t1=new ThreadCreation();
        ThreadCreation t2=new ThreadCreation();
        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t1.start();
        t2.start();
        t1.run();
        t2.run();
    }
}
