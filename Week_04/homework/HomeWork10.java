import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matthew
 * @date 2021-02-07 0:03
 */
public class HomeWork10 {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    // 元子类
    public static void main(String[] args) throws InterruptedException {
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);
        new Thread(() -> {
            sum[0] = homeworkUtil.start();
            atomicInteger.getAndSet(3);
        }).start();
        while (atomicInteger.get() != 3) // 当不等于3时, 不会计算异步值
            Thread.yield();


        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        return;
    }
}
