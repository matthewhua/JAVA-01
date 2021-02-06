import java.util.concurrent.Semaphore;

/**
 * @author Matthew
 * @date 2021-02-06 23:25
 * 设立信号量，开启一个线程，执行完方法后触发信号，然后主线程等待信号
 */
public class HomeWork06 {

    public static Semaphore semaphore = new Semaphore(0); //非公平的

    //由荷兰计算机科学家Dijkstra提出
    //P(等待) V(发信号)

    // 设立信号量
    public static void main(String[] args) throws InterruptedException {
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);
        new Thread(() -> {
            sum[0] = homeworkUtil.start();
            semaphore.release(); //发信号.加一
        }).start();
        semaphore.acquire(); // 等待, 减一
        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }
}
