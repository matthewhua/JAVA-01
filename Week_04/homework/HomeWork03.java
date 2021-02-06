/**
 * @author Matthew
 * @date 2021-02-06 22:22
 */
public class HomeWork03 {

    //匿名内部类 Runnable
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] minus = new int[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                 minus[0] = homeworkUtil.minus(10, 9);
            }
        }).start();
        Thread.sleep(1000L);
        // 确保拿到result 并输出
        System.out.println("异步计算结果为：" + minus[0]);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }
}
