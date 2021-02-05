package class01;

/**
 * @author Matthew
 * @date 2021/2/4 13:28
 */
public class DaemonThread
{

	public static void main(String[] args)
	{
		Runnable task = new Runnable()
		{
			@Override public void run()
			{
				try
				{
					Thread.sleep(5000); //睡罗汉 5秒
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				Thread thread = Thread.currentThread();
				System.out.println("当前线程: " + thread.getName());
			}
		};
		Thread thread = new Thread(task);
		thread.setName("Matthew-thread-1");
		thread.setDaemon(false); //设置是否是 后台进程为false
		thread.start();
	}
}
