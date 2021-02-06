/**
 * @author Matthew
 * @date 2021-02-06 23:36
 */
public class HomeWork07 {
    private static volatile boolean flag = true;

    //设立一个全局标志位，volatile保证主线程和异步线程执行次序，
    // 开启一个线程，执行完方法后变更标志，然后主线程循环标志

    public static void main(String[] args) {
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);
        new Thread(() -> {
            sum[0] = homeworkUtil.start();
            flag = false;
        }).start();
        while (flag)
            Thread.yield();

        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }
}
