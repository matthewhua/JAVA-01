package common_Io;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Matthew
 * @date 2021/1/29 19:22
 */
public class IoUtilsTest
{
	public static void main(String[] args) throws Exception
	{
		InputStream inputStream = new URL("https://commons.apache.org").openStream();
		try
		{
			System.out.println(IOUtils.toString(inputStream));
		}
		finally
		{
			IOUtils.closeQuietly(inputStream);
		}
	}
}
