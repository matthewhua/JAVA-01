package com.matthew.netty.rpc.provider;

import com.matthew.netty.rpc.api.IRpcHelloService;

/**
 * @author Matthew
 * @data 2020/7/1118:25
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "hello" + name + " !";
    }
}
