package io.matthew.rpcfx.demo.consumer.aspect;

import io.matthew.rpcfx.annotation.Reference;
import io.matthew.rpcfx.client.Rpcfx;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Matthew
 * @date 2021/3/20 16:05
 */
@Aspect
@Component
public class ReferencedAspect
{

	@Before("execution(* io.matthew.rpcfx.demo.consumer..*.*(..))")
	public void requestLimit(JoinPoint joinPoint) throws Exception{
		Object target = joinPoint.getTarget();//获取当前类对象
		Field[] fields = target.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			//判断当前字段是否有@Reference注解
			Reference reference = field.getAnnotation(Reference.class);
			if(reference != null){
				//判断当前字段是否为空
				if(field.get(target) == null){
					//生成代理对象
					field.set(target , Rpcfx.create(field.getType() , reference.url()));
				}
			}
		}
	}
}
