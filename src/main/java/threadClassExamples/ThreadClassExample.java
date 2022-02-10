package threadClassExamples;

public class ThreadClassExample {
    public static void main(String[] args) {

        // Java Thread Class runs their codes while the other codes are running.
        // At the same time run two for loops and you will see the mixed output
        new Thread(
                 new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("THREAD " + i );
                }
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("AAA " + i );
        }

    }
}
