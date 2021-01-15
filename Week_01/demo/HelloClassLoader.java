import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @author Matthew
 * @date 2021/1/14 21:02
 */
public class HelloClassLoader extends ClassLoader
{

	public static void main(String[] args)
	{
		try
		{
			Class<?> hello = new HelloClassLoader().findClass("Hello");
			Method declaredMethod = hello.getDeclaredMethod("hello");
			declaredMethod.invoke(hello.newInstance());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}



	}

	@Override protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] classData = loadClassData(name);
		return defineClass(name, classData, 0, classData.length);
	}

	private byte[] loadClassData(String s) throws ClassNotFoundException{
		byte[] xlass = new byte[0];
		try
		{
			xlass = loadFromFile(s.replace(".", File.pathSeparator) + ".xlass");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return xlass;
	}

	private byte[] loadFromFile(String path) throws IOException
	{
		InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int nextValue;
		while ((nextValue = stream.read()) != -1)
		{
			outputStream.write(nextValue);
		}
		return decode(outputStream.toByteArray());
	}

	public byte[] decode(byte[] xlass)
	{
		for (int i = 0; i < xlass.length; i++)
		{
			xlass[i] = (byte) (255 - xlass[i]);
		}
		return xlass;
	}
}
