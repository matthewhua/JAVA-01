import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Matthew
 * @date 2021-02-06 23:45
 */
public class HomeWork08 {

    //建立锁lock，通过信号通知
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);

        new Thread(() -> {
            sum[0] = homeworkUtil.start();
            lock.lock();
           condition.signal();
           lock.unlock();
        }).start();
        lock.lock();
        condition.await(10, TimeUnit.NANOSECONDS); // 建立一个10s超时

        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }

}
