import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Matthew
 * @date 2021-02-07 0:13
 */
public class HomeWork11 {

    static Map<Integer, Thread> map = new ConcurrentHashMap<>();
    public static void main(String[] args) {


        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);
        new Thread(() -> {
            sum[0] = homeworkUtil.start();
            LockSupport.unpark(map.get(1));
        }).start();
        LockSupport.park(map.get(1));

        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        return;
    }
}
