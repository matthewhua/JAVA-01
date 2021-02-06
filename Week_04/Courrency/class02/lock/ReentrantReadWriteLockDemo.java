package class02.lock;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        final Count2 count = new Count2();

        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    count.get();
                }
            }.start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    count.put();
                }
            }.start();
        }
    }

}