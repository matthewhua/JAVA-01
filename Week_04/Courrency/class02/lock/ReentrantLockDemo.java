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

		for (int i = 0; i < 2; i++) {
			new Thread() {
				public void run() {
					count.get();
				}
			}.start();
		}

		for (int i = 0; i < 2; i++)
		{
			new Thread(() -> {
				try {
					count.put();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
