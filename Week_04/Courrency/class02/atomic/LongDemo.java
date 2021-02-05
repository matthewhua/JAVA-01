package class02.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Matthew
 * @date 2021/2/5 10:46
 */
public class LongDemo
{
	public static void main(String[] args)
	{
		final AtomicLong atomicLong = new AtomicLong();
		final LongAdder longAdder = new LongAdder(); // 将单个value拆分成和线程一样的的数据cell[]

		for (int i = 0; i < 100; i++)
		{
			new Thread(new Runnable()
			{
				@Override public void run()
				{
					for (int k = 0; k < 10000; k++)
					{
						atomicLong.incrementAndGet();
						longAdder.increment();
					}
				}
			}).start();
		}

		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("atomicLong=" + atomicLong.get());
		System.out.println("longAdder =" + longAdder.sum());

	}
}
