package class01;

import java.util.concurrent.Callable;

/**
 * @author Matthew
 * @date 2021/2/5 10:57
 */
public class ThreadC implements Callable<String>
{
	//有返回值的
	@Override public String call() throws Exception
	{
		Thread.sleep(1000);
		System.out.println("这是线程C");
		return "线程C";
	}
}
