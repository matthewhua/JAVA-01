package class02.atomic;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

/**
 * @author Matthew
 * @date 2021/2/4 17:33
 */
public class AtomicMain
{
	public static void main(String[] args)
	{
		//final AtomicCount count = new AtomicCount();
		final SyncCount syncCount = new SyncCount();
		for (int i = 0; i < 100; i++)
		{
			new Thread(new Runnable()  //匿名内部类启动
			{
				@Override public void run()
				{
					for (int jk = 0; jk < 1000; jk++)
					{
						//count.add(); //Atomic线程安全相加
						syncCount.add(); // 在写操作上加锁
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

		//System.out.println("num= " + count.getNum());// num= 100000
		System.out.println("加锁相加的num:" + syncCount.getNum()); //加锁相加的num:100000
	}


	@Test
	public void unsafeAddTest()
	{
		final Count count = new Count();
		for (int i = 0; i < 100; i++)
		{
			new Thread(new Runnable()  //匿名内部类启动
			{
				@Override public void run()
				{
					for (int jk = 0; jk < 1000; jk++)
					{
						count.add(); //线程安全相加
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
                                               // 相加数变少了
		System.out.println("num= " + count.getNum());// num= 95002
	}




}
