import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Matthew
 * @date 2021-02-06 23:11
 *
 * 线程池开启一个线程,
 */
public class HomeWork04 {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(10, 1);
        System.out.println("标准计算结果为：" + answer);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Integer> callable = new HomeWork01();
        Future future = executorService.submit(callable);
        Thread.sleep(1000L);
        // 确保拿到result 并输出
        System.out.println("异步计算结果为：" + future.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        return;
    }
}
