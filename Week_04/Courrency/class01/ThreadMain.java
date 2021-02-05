package class01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Matthew
 * @date 2021/2/5 11:04
 */
public class ThreadMain
{

	public static void main(String[] args)
	{

		ThreadA threadA = new ThreadA();
		threadA.start();
		System.out.println("这是主线程A：");

		ThreadB threadB = new ThreadB();
		new Thread(threadB).start();
		System.out.println("这是主线程B ：");

		ThreadC threadC = new ThreadC();
		FutureTask<String> futureTask = new FutureTask<String>(threadC);
		new Thread(futureTask).start();
		System.out.println("This is Main ThreadC : begin! ");

		try
		{
			System.out.println("得到的返回结果是: " + futureTask.get());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
	}
}
