package com.ioc.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 使用factoryBean实例化的bean 比如实例化CityServiceImpl context.getBean("cityService)是不能获取到bean，
 * 而使用context.getBean(CityService.class)则可以获取
 */
@Component
public class CityFactoryBean implements FactoryBean<Object> {
	@Override
	public Object getObject() throws Exception {
		System.out.println("getObject()");
		return new CityServiceImpl();
	}

	@Override
	public Class<?> getObjectType() {
		return CityService.class;
	}
}
