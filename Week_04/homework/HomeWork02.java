import java.util.concurrent.Callable;

/**
 * @author Matthew
 * @date 2021-02-06 22:15
 */
public class HomeWork02 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Callable<Integer> runnable = () -> homeworkUtil.minus(1, 3);
        System.out.println("异步计算结果为：" + runnable.call());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        return;
    }
}
