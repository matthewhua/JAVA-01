package org.matthew.nettyHomeWork.router;

import java.util.List;

/**
 * @author Matthew
 * @date 2021-01-31 0:19
 */
public interface HttpEndpointRouter {

    String route(List<String> endpoints);
}
