package class01.op;

/**
 * @author Matthew
 * @date 2021/2/4 11:44
 */
public class Join
{

	public static void main(String[] args)
	{
		Object o = new Object();

		MyThread myThread = new MyThread("thread --");
		myThread.setAge(o);
		myThread.start();


		synchronized (myThread){
			for (int i = 0; i < 100; i++)
			{
				if (i == 20)
				{
					try
					{
						myThread.join();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " -- " + i);
			}
		}
	}
}

class MyThread extends Thread
{
	private String name;
	private Object age;

	public void setAge(Object oo) {
		this.age = oo;
	}

	public MyThread(String name) {
		this.name = name;
	}

	@Override public void run()
	{
		synchronized (this.name){
			for (int i = 0; i < 100; i++)
			{
				System.out.println(name + i);
			}
		}
	}
}

