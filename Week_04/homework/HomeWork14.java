import java.util.concurrent.Exchanger;

/**
 * @author Matthew
 * @date 2021/2/10 15:42
 */
public class HomeWork14
{
	public static void main(String[] args) throws InterruptedException
	{
		final Exchanger<Integer> exchanger = new Exchanger<>();

		new Thread(() -> {
			try
			{
				exchanger.exchange(homeworkUtil.start());
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}).start();

		final Integer exchange = exchanger.exchange(0);
		System.out.println("res = " + exchange);
	}
}
