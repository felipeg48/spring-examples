/**
 * 
 */
package com.itprosmx.spring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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

	@Test
	public void futureTest() throws InterruptedException, ExecutionException{
		ThreadPoolTaskExecutor task = new ThreadPoolTaskExecutor();
		task.setCorePoolSize(5);
		task.setMaxPoolSize(5);
		task.setWaitForTasksToCompleteOnShutdown(true);
		task.afterPropertiesSet();
		
		
		List<Future<Long>> tasks = new ArrayList<Future<Long>>();
	    int max = 5;
	    for (int i = 0; i < max; i++) {
	        tasks.add(launch(task));
	    }
	    for (Future<Long> taskexec : tasks) {
	        System.out.println("LAUNCH result : " + taskexec.get());
	    }
	}
	
	@SuppressWarnings("unchecked")
	protected Future<Long> launch(ThreadPoolTaskExecutor taskExecutor) {
	    return (Future<Long>) taskExecutor.submit(new FutureTask<Long>(new RunClass()));
	}
	
	protected class RunClass implements Callable<Long>{
		
		public Long call() throws Exception {
			
			Integer sleep = new Random().nextInt(20000)+ 1000;
			System.out.println("Before delay (" + sleep + "): " + Thread.currentThread().getId());
			Thread.sleep(sleep);
			System.out.println("After delay:" + Thread.currentThread().getId());
			
			return Thread.currentThread().getId();
		}
	}
}
