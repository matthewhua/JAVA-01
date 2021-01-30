package org.matthew.nettyHomeWork.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author Matthew
 * @date 2021-01-30 20:59
 */
public interface HttpResponseFilter {

    void filter(FullHttpResponse response);
}
