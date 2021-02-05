package class02.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Matthew
 * @date 2021/2/5 16:08
 */
public class Count
{
	final ReentrantLock lock = new ReentrantLock(false);


	public void get() throws InterruptedException
	{
		lock.lock(); // 上锁
		System.out.println(Thread.currentThread().getName() + " get begin");
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName() + " get end");
		lock.unlock();
	}

	public void put() throws InterruptedException
	{
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "put begin");
		Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName() + "put end");
		lock.unlock();
	}
}
