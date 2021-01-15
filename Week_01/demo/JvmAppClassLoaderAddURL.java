import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Matthew
 * @date 2021/1/13 23:58
 */
public class JvmAppClassLoaderAddURL
{
	public static void main(String[] args)
	{
		String pathTo = "file:/D:/jikeHomeWork/JAVA-01/Week_01/demo/";
		URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();

		try
		{
			Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			addURL.setAccessible(true);// 强吻
			URL url = new URL(pathTo);
			addURL.invoke(urlClassLoader, url);
			Class.forName("Greeting");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
