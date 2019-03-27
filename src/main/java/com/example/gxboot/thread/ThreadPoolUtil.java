package com.example.gxboot.thread;

import java.util.concurrent.*;

/**
 * ClassName: ThreadPoolUtil. <br/>
 * Description: 线程池工具类. <br/>
 * Date: 2019年01月19日 <br/>
 * 
 * @author zhuyunfei
 * @version 1.0.0
 * @since 1.7
 */
public class ThreadPoolUtil {
	/**
	 * 创一个线程池对象
	 * @param threadName 线程名称
	 * @param threadCount 线程数量
	 * @return
	 */
	public static ExecutorService createFixedThreadPool(String threadName,int threadCount,int queueSize) {

		// 定义线程工厂对象
		ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
		        .setNamePrefix(threadName).build();
				
		// 定义线程池对象
		ExecutorService executorService = new ThreadPoolExecutor(threadCount, threadCount,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize),customThreadfactory,new ThreadPoolExecutor.AbortPolicy());
		
		return executorService;
	}
}
