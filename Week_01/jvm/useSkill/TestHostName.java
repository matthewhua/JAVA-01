package useSkill;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author Matthew
 * @date 2021-01-23 15:55
 */
public class TestHostName
{
    //运行时信息, 可以获取各种环境
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        String[] vmInfos = name.split("@");
        String processId = vmInfos[0]; // 进场Id
        //long pid = runtimeMXBean.getPid();  //JDK 10 之后
        String hostname = vmInfos[1];
        System.out.println("vmId =" + name);
        System.out.println("processId =" + processId);
        System.out.println("hostname =" + hostname);


        Runtime runtime = Runtime.getRuntime();
        System.out.println("CPU 数量:" + runtime.availableProcessors());
        System.out.println("JVM最大内存: " + runtime.maxMemory() / (8 * 1024 * 1024) + "MB"); //2021 默认启动JVM 为 453 MB
        System.out.println("JVM当前内存: " + runtime.totalMemory());
        System.out.println("JVM空闲内存: " + runtime.freeMemory());
    }

}
