package com.crystal.spring_web_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class WebConfig {
	@Bean
	ThreadPoolTaskExecutor executor () { 
		ThreadPoolTaskExecutor executor =  new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(32);
		executor.setMaxPoolSize(200);
		executor.setKeepAliveSeconds(30);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}
}
