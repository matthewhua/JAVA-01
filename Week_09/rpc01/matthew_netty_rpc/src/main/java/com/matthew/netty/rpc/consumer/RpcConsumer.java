package com.matthew.netty.rpc.consumer;

import com.matthew.netty.rpc.api.IRpcHelloService;
import com.matthew.netty.rpc.api.IRpcService;
import com.matthew.netty.rpc.consumer.proxy.RpcProxy;

/**
 * @author Matthew
 * @data 2020/7/1115:57
 */
public class RpcConsumer {

    public static void main(String[] args) {
        IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHello.hello("今晚打飞机。。。。"));

        IRpcService service = RpcProxy.create(IRpcService.class);

        System.out.println("8 + 2 = " + service.add(8, 2));
        System.out.println("8 - 4 = " + service.sub(8, 2));
        System.out.println("8 * 2 = " + service.mult(8, 2));
        System.out.println("8 / 2 = " + service.div(8, 2));
    }
}
