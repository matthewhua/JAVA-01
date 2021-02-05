package class02.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Matthew
 * @date 2021/2/5 13:48
 */
public class PoolExceptionDemo
{
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newFixedThreadPool(1);

		Future<Double> future = executorService.submit(() -> {
			int a = 1;
			return 10.0 / (a - 1);
		});

		try
		{
			Double aDouble = future.get();
		}
		catch (Exception e)
		{
			System.out.println("catch execute.. ");
			e.printStackTrace();
		}

		try
		{
			executorService.execute(() -> {
				int a = 1;
				float b = 10 / (a-1);
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


		executorService.shutdown(); // 线程好像会先关闭
		System.out.println("Main Thread End!");
	}
}
