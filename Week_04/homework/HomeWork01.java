import java.util.concurrent.Callable;

/**
 * @author Matthew
 * @date 2021-02-06 22:07
 * 继承Callable 实现
 */
public class HomeWork01 implements Callable<Integer> {


    public static void main(String[] args) {
        HomeWork01 call = new HomeWork01();
        long start = System.currentTimeMillis();
        Integer call1 = call.call();
        System.out.println("异步计算结果为："+ call1);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }


    @Override
    public  Integer call()  {
        return homeworkUtil.minus(10, 9);
    }
}
