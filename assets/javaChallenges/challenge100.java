public class challenge100 extends Thread {
    public void run(){

           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
        System.out.println("The thread is running,"+getState());

       }

    }



