package org.matthew.nettyHomeWork.outbound.okhttp;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.matthew.nettyHomeWork.outbound.AbstractHttpOutboundHandler;

import java.io.IOException;
import java.util.Set;

/**
 * @author Matthew
 * @date 2021-01-30 21:29
 */
public class OKHttpOutboundHandler extends AbstractHttpOutboundHandler {

    @Override
    public String sendRequest(FullHttpRequest fullHttpRequest, String Uri) {
        System.out.println("开始执行OkHttp 啦");

        OkHttpClient okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        HttpHeaders headers = fullHttpRequest.headers();
        Set<String> names = headers.names();
        names.stream().filter(n -> !"Host".equals(n)).forEach(h -> builder.header(h, headers.get(h)));
        Request getRequest = builder.get().url(Uri).build();
        System.out.println(getRequest.headers().toString());

        try {
            Response response = okHttpClient.newCall(getRequest).execute();
            if(response.isSuccessful())
            {
                String rspVal = response.body().string();
                System.out.println(" response value is :" + rspVal);
                return rspVal;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "";
    }
}
