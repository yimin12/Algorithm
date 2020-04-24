/**
 * 
 */
package practise.concurrency.example;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午2:55:52
* Description:
* 	但是有些时候，这种保护有点过头。因为我们发现，任何时刻，只允许一个线程修改，也就是调用inc()方法是必须获取锁，但是，get()方法只读取数据，不修改数据，它实际上允许多个线程同时调用。
* 	实际上我们想要的是：允许多个线程同时读，但只要有一个线程在写，其他线程就必须等待：
* 		使用ReadWriteLock可以解决这个问题，它保证：
* 			只允许一个线程写入（其他线程既不能写入也不能读取）;
* 			没有写入时，多个线程允许同时读（提高性能）
*/

public class TestReadWriteLock {
	private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
	private final Lock rlock = rwlock.readLock();
	private final Lock wlock = rwlock.writeLock();
	private int[] cnts = new int[10];
	
	public void inc(int index) {
		// evolved in writing operation
		wlock.lock();
		try {
			cnts[index] += 1;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			wlock.unlock();
		}
	}
	
	public int[] get() {
		rlock.lock();
		try {
			return Arrays.copyOf(cnts, cnts.length);
		}  finally {
			rlock.unlock();
		}
	}
}
