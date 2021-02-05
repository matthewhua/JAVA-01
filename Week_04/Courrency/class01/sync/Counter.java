package class01.sync;

/**
 * @author Matthew
 * @date 2021/2/5 11:12
 */
public class Counter
{
	public final static int A = 20;
	public static int B=10;

	private volatile int sum = 0;

	public synchronized void incr(){
		sum = sum + 1;
	}

	public int getSum(){
		return sum;
	}

	public static void main(String[] args) throws InterruptedException
	{
		int loopNum = 1000000000;
		Counter counter = new Counter();
		long singleStartTime = System.currentTimeMillis();
		//single thread test
		for (int i = 0; i < loopNum; i++)
		{
			counter.incr();
		}
		long gapTime = System.currentTimeMillis() - singleStartTime;
		System.out.println("single thread :" + counter.getSum() + "消耗用时: " + gapTime); //2

		//multipe threads test
		Counter counter1 = new Counter();

		long multipeStartTime = System.currentTimeMillis();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < loopNum / 2; i++)
			{
				counter1.incr();
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < loopNum / 2; i++)
			{
				counter1.incr();
			}
		});
		t1.start();
		t2.start();
		Thread.sleep(1000);

		while (Thread.activeCount() > 2)
		{
			Thread.yield();
		}
		long gapTime2 = System.currentTimeMillis() - multipeStartTime;
		System.out.println("multiple threads: " + counter1.getSum() + "消耗用时: " + gapTime2) ; //1037

		// 在数据因为上下文开销, 反而没有单线程来得快
	}
}
