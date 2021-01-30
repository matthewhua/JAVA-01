package org.matthew.nettyHomeWork.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author Matthew
 * @date 2021-01-30 21:17
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter{

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("Matthew", "game Programmer");
    }
}
