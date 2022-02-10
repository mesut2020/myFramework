package threadClassExamples;

public class Multithreading2 {

    public static void main(String[] args) {

        for (int i = 0; i <= 5; i++) {
            MultithreadThing2 myThing = new MultithreadThing2(i);
            Thread myThread = new Thread(myThing);
            myThread.start();
            System.out.println(myThread.isAlive()); // it returns true or false for whether the thread is currently still running
            try {
                myThread.join();  // it waits for another to complete
            } catch (InterruptedException e) {
            }
        }
    }

}

class MultithreadThing2 implements Runnable {
    private int threadNumber;
    public MultithreadThing2 (int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " from thread " + threadNumber);
            if (threadNumber == 3) {
               // throw new RuntimeException();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
