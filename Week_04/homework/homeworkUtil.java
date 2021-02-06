/**
 * @author Matthew
 * @date 2021-02-06 21:58
 */
public class homeworkUtil {

    public static Integer start()
    {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        int result = minus(32, 9); //这是得到的返回值
        System.out.println("计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        return result;
    }

    protected static int minus(int a, int b)
    {
        return a - b > 0 ? a - b : b - a;
    }


}
