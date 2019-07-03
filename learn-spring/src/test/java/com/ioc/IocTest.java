package com.ioc;

import com.ioc.service.CityService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest {

	public static void main(String[] args) {
		//spring 环境
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println(context.getBean("cityService"));
		System.out.println(context.getBean(CityService.class));

	}
}
