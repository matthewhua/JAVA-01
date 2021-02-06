
package class02.lock;

public class LockMain {

    public static void main(String[] args) {
        Count3 count3 = new Count3();
        ThreadA threadA = new ThreadA(count3);
        threadA.setName("线程A");
        threadA.start();

        ThreadB threadB = new ThreadB(count3);
        threadB.setName("线程B");
        threadB.start();

        System.out.println("这是线程: " + Thread.currentThread().getName());
    }

}
