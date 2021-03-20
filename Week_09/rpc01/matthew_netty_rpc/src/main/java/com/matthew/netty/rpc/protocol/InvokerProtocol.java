package com.matthew.netty.rpc.protocol;

/**
 * @author Matthew
 * @data 2020/7/1116:19
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义传输协议
 */
@Data
public class InvokerProtocol implements Serializable {

    private String className; //类名
    private String methodName; //函数名称
    private Class<?>[] params; //形式参数
    private Object[] values;//实参列表

}
