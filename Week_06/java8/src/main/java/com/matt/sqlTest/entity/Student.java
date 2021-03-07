package com.matt.sqlTest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * @author Matthew
 * @date 2021/2/25 19:42
 */
@Data
@NoArgsConstructor
@ToString
@Slf4j
public class Student implements Serializable, ApplicationContextAware
{
	private int id;
	private String name;

	private ApplicationContext applicationContext;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void init(){
		System.out.println("hello...........");
		log.info("mmathe");
	}

	public Student create(){

		this.applicationContext.getBeanDefinitionNames();
		return  new Student(111,"Mat101");
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}
}
