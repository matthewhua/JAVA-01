package class01;

/**
 * @author Matthew
 * @date 2021/2/4 13:31
 */
public class Runner2 implements Runnable
{
	@Override public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			System.out.println("进入Runner2运行状态------------" + i);
		}

		boolean result1 = Thread.currentThread().isInterrupted(); // 是否是中断
		boolean result2 = Thread.interrupted();  //重置状态

		boolean result3 = Thread.currentThread().isInterrupted();

		System.out.println("Runner2.run result1 ===>" + result1);
		System.out.println("Runner2.run result2 ===>" + result2);
		System.out.println("Runner2.run result3 ===>" + result3);
	}
}
