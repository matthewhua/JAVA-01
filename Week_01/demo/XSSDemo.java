/**
 * @author Matthew
 * @date 2021/1/14 9:41
 */
public class XSSDemo
{
	public static int MB = 1024 * 1024;
	public static void main(String[] args)
	{
		int index = 0;
		while (index++ < 10000){
			int c = index;
			new Thread( () -> {
				System.out.println(c + " -> " + Runtime.getRuntime().totalMemory()/MB +"MB "
						+ Runtime.getRuntime().maxMemory()/MB   +"MB "
						+ Runtime.getRuntime().freeMemory()/MB  +"MB ");
				try {
					Thread.sleep(2000);
				}catch (Exception e){

				}
			}).start();
		}

	}
}
