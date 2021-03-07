package matt.dataSource.aspect;

import matt.dataSource.annotation.SwitchDs;
import matt.dataSource.config.DataSources;
import matt.dataSource.config.DynamicDataSource;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author Matthew
 * @date 2021-03-07 21:24
 */
@Aspect
public class MyDataSourceAspect {

    @Autowired
    private DataSources dataSources;

    private final static Logger logger = LoggerFactory.getLogger(MyDataSourceAspect.class);


    @Around(value = "@annotation(switchDs) || @within(matt)", argNames = "point, switchDs")
    public Object setDataSource(ProceedingJoinPoint point, SwitchDs switchDs) throws Throwable {
        //可能事务代理会导致获取不到值，所以重新手动获取
        final MethodSignature signature = (MethodSignature) point.getSignature();
        final Class<?> target = point.getTarget().getClass();
        final Method method = signature.getMethod();
        final Class<?> clazz = target.getInterfaces()[0];
        if (method.isAnnotationPresent(SwitchDs.class)) {
            switchDs = method.getAnnotation(SwitchDs.class);
        } else if (clazz.isAnnotationPresent(SwitchDs.class)){
            switchDs = clazz.getAnnotation(SwitchDs.class);
        } else {
            //没有注解，直接返回
            return point.proceed(point.getArgs());
        }

        String dataSource = switchDs.value();
        boolean isReadOnly = switchDs.readOnly();
        if (StringUtils.isNotBlank(dataSource)) {
            DynamicDataSource.setDataSource(dataSource);
        } else if (isReadOnly) {
            Random random = new Random(System.currentTimeMillis());
            DynamicDataSource.setDataSource(dataSources.getSlaves().get(random.nextInt(dataSources.getSlaves().size())));
        } else {
            throw new RuntimeException("请检查数据源配置");
        }

        logger.debug("切换数据源为：{}", dataSource);
        Object result;
        try {
            result = point.proceed(point.getArgs());
        } finally {
            DynamicDataSource.reset();
        }
        return result;
    }
}
