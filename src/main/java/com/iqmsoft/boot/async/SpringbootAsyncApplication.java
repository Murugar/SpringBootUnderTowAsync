package com.iqmsoft.boot.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SpringbootAsyncApplication {
	
	
	 @Bean("taskExecutorAsyncPool")
	 public TaskExecutor taskExecutor() {

	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(4);
	        executor.setMaxPoolSize(10);
	        executor.setQueueCapacity(25);

	        return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}
}
