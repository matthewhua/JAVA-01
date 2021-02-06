import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Matthew
 * @date 2021-02-06 23:56
 */
public class HomeWork09 {

    private static BlockingQueue<String> blockingQueue;

    public static void main(String[] args) throws InterruptedException {
        blockingQueue = new SynchronousQueue<>(); //非公平同步队列
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);
        new Thread(() -> {
            sum[0] = homeworkUtil.start();
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        blockingQueue.put("是时候展示真正的技术了!");
        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");

        return;
    }
}
