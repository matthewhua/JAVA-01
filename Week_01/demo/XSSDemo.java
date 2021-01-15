/**
 * @author Matthew
 * @date 2021/1/14 9:41
 */
public class XSSDemo
{
	public static void main(String[] args)
	{
		int index = 0;
		while (index++ < 10000){
			int c = index;
			new Thread( () -> {
				System.out.println(c + " -> " + Runtime.getRuntime().totalMemory() +"MB "
						+ Runtime.getRuntime().maxMemory()   +"MB "
						+ Runtime.getRuntime().freeMemory()  +"MB ");
				try {
					Thread.sleep(2000);
				}catch (Exception e){

				}
			}).start();
		}

	}
}
