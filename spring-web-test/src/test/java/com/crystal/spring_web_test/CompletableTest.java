package com.crystal.spring_web_test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class CompletableTest {
	private ThreadPoolTaskExecutor executor;
	private Video video = new Video(1);
	
	public class Video {
		private int id;
		public Video(int id) {
			this.id = id;
		}
		public int getId() {
			return this.id;
		}
	}
	
	@Before
	public void setUp() {
		executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(8);
		executor.setQueueCapacity(100);
		executor.setKeepAliveSeconds(5);
		executor.setThreadNamePrefix("threadpool-executor-");
		executor.initialize();
	}
	
	
	@Test
	public void testCompletable() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> delete(video), executor).thenRunAsync(() -> deleteRedis(video), executor);
		try {
			future.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Video video) {
		System.out.println("delete video from db : " + video.getId() +" thread : " + Thread.currentThread().getName());
	}
	public void deleteRedis(Video video) {
		System.out.println("delete video from redis : " + video.getId() +" thread : " + Thread.currentThread().getName());
	}
}
