/**
 * @author Matthew
 * @date 2021-02-07 0:39
 */
public class HomeWork13 {

    public static void main(String[] args) throws InterruptedException {


        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = homeworkUtil.minus(2232313, 988787);
        System.out.println("标准计算结果为：" + answer);

        Thread thread = new Thread(() -> {
            sum[0] = homeworkUtil.start();
        });

        thread.start();
        thread.join(); //等待线程死亡

        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("异步使用时间：" + (System.currentTimeMillis() - start) + " ms");
        return;
    }
}
