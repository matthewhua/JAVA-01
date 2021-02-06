import java.util.concurrent.CountDownLatch;

/**
 * @author Matthew
 * @date 2021-02-06 23:15
 */
public class HomeWork05 {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sum[0] = homeworkUtil.start();
                countDownLatch.countDown(); //到零时会释放所有
            }
        }).start();
        countDownLatch.await();
        // 确保拿到result 并输出
        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }
}
