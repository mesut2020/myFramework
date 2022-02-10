package threadClassExamples;

public class ThreadExample {
    public static void main(String[] args) {
        Data d = new Data();
        d.value = 100;
        d.flag = false;
        Producer p = new Producer(d);
        Consumer c = new Consumer(d);
        Thread t = new Thread(c);
        p.start();
        t.start();
    }
}

class Producer extends Thread {
    Data d;

    public Producer(Data d) {
        this.d = d;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (d) {
                if(d.flag==false) {
                    d.value++;
                    System.out.println("threadClassExamples.Producer " + d.value);
                    d.flag=true;
                }
            }
        }
    }
}

class Consumer implements Runnable { // recommended
    Data d;

    public Consumer(Data d) {
        this.d = d;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (d) {
                if(d.flag) {
                    d.value--;
                    System.out.println("threadClassExamples.Consumer " + d.value);
                    d.flag=false;
                }
            }
        }
    }
}

class Data {
    int value;
    boolean flag;
}
