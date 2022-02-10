package threadClassExamples;

public class Multithreading {

    public static void main(String[] args) {

        for (int i = 0; i <= 3; i++) {
            MultithreadThing myThing = new MultithreadThing(i);
            myThing.start();
        }

        throw new RuntimeException();

    }

}

class MultithreadThing extends Thread {
    private int threadNumber;
    public MultithreadThing (int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " from thread " + threadNumber);
            if (threadNumber == 3) {
                throw new RuntimeException();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
