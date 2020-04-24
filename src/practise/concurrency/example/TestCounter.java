/**
 * 
 */
package practise.concurrency.example;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午1:09:32
* Description:
* 	当多个线程同时运行时，线程的调度由操作系统决定，程序本身无法决定。因此，任何一个线程都有可能在任何指令处被操作系统暂停，然后在某个时间段后继续执行。 这个时候，有个单线程模型下不存在的问题就来了：如果多个线程同时读写共享变量，会出现数据不一致的问题。
*/

public class TestCounter {
	
	public static void main(String[] args) throws Exception{
//		AddThread add = new AddThread();
//		DecThread dec = new DecThread();
//		add.start();
//		dec.start();
//		add.join();
//		dec.join();
//		System.out.println(Counter.count);
		
		// synchronized
		AddThreadSyn add = new AddThreadSyn();
		DecThreadSyn dec = new DecThreadSyn();
		add.start();
		dec.start();
		add.join();
		dec.join();
		System.out.println(Counter.count);
	}
}
class Counter{
	public static int count = 0;
}
class AddThread extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			Counter.count += 1;
		}
	}
}
class DecThread extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			Counter.count -= 1;
		}
	}
}


class CounterSyn{
	public static int count = 0; 
	public static final Object lock = new Object();
}
class AddThreadSyn extends Thread{
	@Override
	public void run() {
		synchronized (CounterSyn.lock) {
			for(int i = 0; i < 10000; i++) {
			Counter.count += 1;
			}
		}
	}
}
class DecThreadSyn extends Thread{
	@Override
	public void run() {
		synchronized (CounterSyn.lock) {
			for(int i = 0; i < 10000; i++) {
			Counter.count -= 1;
			}
		}	
	}
}

