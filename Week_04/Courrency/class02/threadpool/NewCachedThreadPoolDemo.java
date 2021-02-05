package class02.threadpool;

import java.util.concurrent.*;

/**
 * @author Matthew
 * @date 2021/2/5 13:55
 */
public class NewCachedThreadPoolDemo
{
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 10000; i++)
		{
			final int no = i;
			Runnable runnable = new Runnable()
			{
				@Override public void run()
				{
					try
					{
						System.out.println("start: " + no);
						Thread.sleep(1000L);
						System.out.println("end:" + no);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);
		}
		executorService.shutdown();
	}
}
