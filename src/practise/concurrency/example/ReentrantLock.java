/**
 * 
 */
package practise.concurrency.example;

import java.util.concurrent.locks.Lock;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午2:41:05
* Description:
*/
public class ReentrantLock {

	
}

// Common Counter with syn
class CounterCommon{
	private int count;
	public void add(int n) {
		synchronized (this) {
			count += n;
		}
	}
}
// If we use ReentrantLock
class CounterRe{
	private final Lock lock = (Lock) new ReentrantLock();
	private int count;
	
	public void add(int n) {
		lock.lock();
		try {
			count += n;
		} catch (Exception e) {
			lock.unlock();
		}
	}
}