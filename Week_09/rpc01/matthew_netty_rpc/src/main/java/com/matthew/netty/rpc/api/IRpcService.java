package com.matthew.netty.rpc.api;

/**
 * @author Matthew
 * @data 2020/7/1115:56
 */
public interface IRpcService {

    /** 加 */
    public int add(int a,int b);

    /** 减 */
    public int sub(int a,int b);

    /** 乘 */
    public int mult(int a,int b);

    /** 除 */
    public int div(int a,int b);
}
