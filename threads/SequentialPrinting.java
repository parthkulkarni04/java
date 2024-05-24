
public class SequentialPrinting {

    private static final int MAX_NUMBER = 10;
    private static int currentNumber = 1;

    public static void main(String[] args) {
        Object lock = new Object();
        
        // Creating multiple threads
        for (int i = 0; i < 3; i++) { // Creating 3 threads for example
            Thread thread = new Thread(new NumberPrinter(lock));
            thread.setName("Thread " + (i + 1));
            thread.start();
        }
    }

    static class NumberPrinter implements Runnable {
        private final Object lock;

        public NumberPrinter(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > MAX_NUMBER) {
                        lock.notifyAll();
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " printed " + currentNumber);
                    currentNumber++;
                    try {
                        Thread.sleep(1000); // 1 second delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
