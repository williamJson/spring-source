package com.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring配置类
 *
 * @Configuration 注解 就相当于在spring-application.xml
 */
@Configuration
@ComponentScan("com.ioc")
public class AppConfig {


}
