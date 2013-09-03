/**
 * 
 */
package com.itprosmx.spring.test;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author fgutierrezcru
 *
 */
public class MultiThreadMain {
	
	@Test
	public void test() {
		ThreadPoolTaskExecutor task = new ThreadPoolTaskExecutor();
		task.setCorePoolSize(5);
		task.setMaxPoolSize(5);
		task.setWaitForTasksToCompleteOnShutdown(true);
		task.afterPropertiesSet();
		
		Runnable run = new Runnable() {
			public void run(){
				try{
					System.out.println(Thread.currentThread().getId());
					Thread.sleep(500);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		};
		
		for(int k=0; k<5; ++k) {
			task.execute(run);
		}
	}

}
