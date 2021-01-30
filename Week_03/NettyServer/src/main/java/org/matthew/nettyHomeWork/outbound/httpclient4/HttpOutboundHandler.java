package org.matthew.nettyHomeWork.outbound.httpclient4;

import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.matthew.nettyHomeWork.outbound.AbstractHttpOutboundHandler;

import java.io.IOException;

/**
 * @author Matthew
 * @date 2021-01-30 21:28
 */
public class HttpOutboundHandler extends AbstractHttpOutboundHandler {

    @Override
    public String sendRequest(FullHttpRequest fullHttpRequest, String Uri) {
        System.out.println("HttpClient 执行啦...");
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(Uri);
        CloseableHttpResponse response;

        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
                return content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
