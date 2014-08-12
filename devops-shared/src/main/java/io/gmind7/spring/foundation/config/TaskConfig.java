package io.gmind7.spring.foundation.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ListeningThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableAsync(mode=AdviceMode.PROXY) // <task:*>
@EnableScheduling
public class TaskConfig implements SchedulingConfigurer, AsyncConfigurer {
	
    /*
     <task:annotation-config scheduler="taskScheduler"/>
     <task:executor id="taskExecutor" pool-size="50"/>
     <task:scheduler id="taskScheduler" pool-size="100"/>
    */
	
	@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
    }

    @Bean
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(100);
    }
    
    @Override
    public Executor getAsyncExecutor() {
        return taskExecutor();
    }
    
    @Bean
    public Executor taskExecutor() {
    	ListeningThreadPoolTaskExecutor executor = new ListeningThreadPoolTaskExecutor();
    	executor.setCorePoolSize(40);
    	executor.setMaxPoolSize(50);
    	executor.setQueueCapacity(100);
    	return executor;
    }

}
