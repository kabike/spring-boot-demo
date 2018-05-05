package com.fake.god.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new FooInterceptor())
				.addPathPatterns("/**");

	}

	@Bean
	public MappedInterceptor getMappedInterceptor() {
		return new MappedInterceptor(new String[] { "/**" }, new BarInterceptor());
	}
}
