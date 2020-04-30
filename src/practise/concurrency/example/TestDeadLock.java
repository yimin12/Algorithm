/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午1:44:14
* Description:
* 
*/
public class TestDeadLock {
	
	static final Object LOCK_A = new Object();
	static final Object LOCK_B = new Object();
	
	static void sleepOneSecs() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Thread11().start();
		new Thread22().start();
	}
}
class Thread11 extends Thread{
	@Override
	public void run() {
		System.out.println("Thread-1: try get lock A....");
		synchronized (TestDeadLock.LOCK_A) {
			System.out.println("Thread-1: lock A, got");
			TestDeadLock.sleepOneSecs();
			System.out.println("Thread-1: try to get lock B...");
			synchronized (TestDeadLock.LOCK_B) {
				System.out.println("Thead-1: Lock B, got");
				TestDeadLock.sleepOneSecs();
			}
			System.out.println("Thread-1: lock B release");
		}
		System.out.println("Thread-1: lock A release");
	}
}

class Thread22 extends Thread{
	public void run() {
		System.out.println("Thread-2: try get lock B...");
		synchronized (TestDeadLock.LOCK_B) {
			System.out.println("Thread-2: lock B got.");
			TestDeadLock.sleepOneSecs();
			System.out.println("Thread-2: try get lock A...");
			synchronized (TestDeadLock.LOCK_A) {
				System.out.println("Thread-2: lock A got.");
				TestDeadLock.sleepOneSecs();
			}
			System.out.println("Thread-2: lock A released.");
		}
		System.out.println("Thread-2: lock B released.");
	}
}
