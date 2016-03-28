package me.jinkun.common.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *	线性管理类
 */
public class ThreadManager {
	//1,创建一个线程的管理者的代理对象
	private static ThreadProxyPool threadProxyPool;
	private static Object object = new Object();
	//2,创建线程池代理管理者对象
	public static ThreadProxyPool getThreadProxyPool(){
		synchronized (object) {
			if(threadProxyPool == null){
				threadProxyPool = new ThreadProxyPool(3, 5, 5L);
			}
		}
		return threadProxyPool;
	}
	
	/**
	 *	用于执行线程中的任务,以及管理线程
	 */
	public static class ThreadProxyPool{
		private int corePoolSize;
		private int maximumPoolSize;
		private long keepAliveTime;
		private ThreadPoolExecutor threadPoolExecutor;
		
		public ThreadProxyPool(int corePoolSize,int maximumPoolSize,long keepAliveTime){
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}
		/**
		 * 将线程中需要执行的代码封装到任务中,然后给此方法执行
		 * @param runnable
		 */
		public void execute(Runnable runnable){
			if(runnable!=null){
				if(threadPoolExecutor == null || threadPoolExecutor.isShutdown()){
					threadPoolExecutor = new ThreadPoolExecutor(
							//核心线程数
							corePoolSize, //3
							//最大线程数
							maximumPoolSize, //5
							//线程在空闲的最大存活时间
							keepAliveTime, //5
							//线程存活时间单位
							TimeUnit.MILLISECONDS,//毫秒
							//任务队列
							new LinkedBlockingQueue<Runnable>(), 
							//线程工程
							Executors.defaultThreadFactory());
				}
				threadPoolExecutor.execute(runnable);
			}
		}
	}
}
