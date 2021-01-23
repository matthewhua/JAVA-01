package reflect;

import java.lang.reflect.Method;

/**
 * @author Matthew
 * @date 2021-01-23 15:10
 */
public class Test {
    public static void target(int i){
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> test = Class.forName("reflect.Test");
        Method method = test.getMethod("target", int.class);
        method.invoke(null, 0);
    }
}
