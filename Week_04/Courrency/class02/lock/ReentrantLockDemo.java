package class02.lock;

/**
 * @author Matthew
 * @date 2021/2/5 16:14
 */
public class ReentrantLockDemo
{
	public static void main(String[] args)
	{
		final Count count = new Count();

		for (int i = 0; i < 3; i++)
		{
			new Thread(){
				@Override public void run()
				{
					super.run();
				}
			}.start();
		}

		for (int i = 0; i < 3; i++)
		{
			new Thread(() -> )
		}
	}
}
