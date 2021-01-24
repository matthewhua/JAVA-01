import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Matthew
 * @date 2021-01-24 6:43
 */
public class HttpClientTest {

    //http 请求localhost
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet =new HttpGet("http://localhost:8802");
        CloseableHttpResponse response  = client.execute(httpGet);
        System.out.println("响应状态为: " + response.getStatusLine());
        if (response.getEntity() != null)
        {
            System.out.println("响应内容长度为:" + response.getEntity().getContentLength());
            System.out.println("响应内容类型为" + response.getEntity().getContentType());
            System.out.println("响应内容编码为" + response.getEntity().getContentEncoding());
            System.out.println(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        }
    }
}
