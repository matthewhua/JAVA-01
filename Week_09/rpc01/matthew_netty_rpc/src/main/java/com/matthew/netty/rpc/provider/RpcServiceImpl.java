package com.matthew.netty.rpc.provider;


import com.matthew.netty.rpc.api.IRpcService;

/**
 * @author Matthew
 * @data 2020/7/1118:25
 */
public class RpcServiceImpl implements IRpcService {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mult(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }
}
