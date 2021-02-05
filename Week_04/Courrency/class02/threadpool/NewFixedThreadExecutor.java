package class02.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Matthew
 * @date 2021/2/5 15:33
 */
public class NewFixedThreadExecutor
{
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(16);
		for (int i = 0; i < 100; i++) {
			final int no = i;

			executorService.submit(new Callable<Object>()
			{
				@Override public Object call() throws Exception
				{
					System.out.println("Callable start:" + no);
					Thread.sleep(1000L);
					System.out.println("Callable end:" + no);
					return "Call is succeeded";
				}
			});
			executorService.execute(() -> {
				try {
					System.out.println("Runnable start:" + no);
					Thread.sleep(1000L);
					System.out.println("Runnable end:" + no);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
		System.out.println("Main Thread End!");
	}
}
