package com.ioc.service;

import org.springframework.stereotype.Component;

@Component
public class CityServiceImpl implements CityService {
	@Override
	public void say(String arg) {
		System.out.println("this is cityServiceImpl");
	}
}
