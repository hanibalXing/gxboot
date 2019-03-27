package com.example.gxboot.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName: CustomThreadFactoryBuilder <br/>
 * Description:自定义线程工厂生成类.<br/>
 * Date: 2019年01月22日 <br/>
 *
 * @author zhuyunfei
 * @version 1.0.0
 * @since 1.7
 */
public class CustomThreadFactoryBuilder {

	/**
	 * 线程名称前缀
	 */
	private String namePrefix = null;
	
	/**
	 * 是否为守护线程
	 */
    private boolean daemon = false;
    
    /**
     * 线程优先级
     */
    private int priority = Thread.NORM_PRIORITY;

    public CustomThreadFactoryBuilder setNamePrefix(String namePrefix) {
        if (namePrefix == null) {
            throw new NullPointerException();
        }
        this.namePrefix = namePrefix;
        return this;
    }

    public CustomThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    /**
     * 设置线程执行优先级
     * @param priority
     * @return
     */
    public CustomThreadFactoryBuilder setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY){
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY));
        }

        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be <= %s", priority, Thread.MAX_PRIORITY));
        }

        this.priority = priority;
        return this;
    }

    public ThreadFactory build() {
        return build(this);
    }

    /**
     * 构造一个线程池工厂对象
     * @param builder
     * @return
     */
    private static ThreadFactory build(CustomThreadFactoryBuilder builder) {
        final String namePrefix = builder.namePrefix;
        final Boolean daemon = builder.daemon;
        final Integer priority = builder.priority;
        final AtomicLong count = new AtomicLong(0);
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                if (namePrefix != null) {
                    thread.setName(namePrefix + "-" + count.getAndIncrement());
                }
                if (daemon != null) {
                    thread.setDaemon(daemon);
                }
                if (priority != null) {
                    thread.setPriority(priority);
                }
                return thread;
            }
        };
    }
}
