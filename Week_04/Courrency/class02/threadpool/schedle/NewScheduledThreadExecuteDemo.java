package class02.threadpool.schedle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Matthew
 * @date 2021/2/5 15:11
 */
public class NewScheduledThreadExecuteDemo
{

	public static void main(String[] args)
	{
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(16);

		for (int i = 0; i < 100; i++)
		{
			final int no = i;
			Runnable runnable = new Runnable()
			{
				@Override public void run()
				{
					System.out.println("start:" + no);
					try
					{
						Thread.sleep(1000L);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					System.out.println("end:" + no);
				}
			};
			// 过会执行
			executorService.schedule(runnable, 10, TimeUnit.SECONDS);
		}

		executorService.shutdown();
		System.out.println("Main Thread End");
	}
}
