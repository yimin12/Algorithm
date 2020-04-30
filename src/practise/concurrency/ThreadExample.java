/**
 * 
 */
package practise.concurrency;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月19日 下午5:42:52
* Description:
*/

public class ThreadExample {

	public static void main(String[] args) {
//		System.out.println(Thread.currentThread().getName());
//		for(int i = 0; i < 10; i++) {
//			new Thread("" + i) {
//				public void run() {
//					System.out.println("Thread: " + getName() + " running");
//				};
//			}.start();
//		}
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);
		thread.start();
		try {
			Thread.sleep(10L * 1000L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		myRunnable.doStop();
	}
}

class MyRunnable implements Runnable{
	private boolean flag = false;
	
	public synchronized void doStop() {
		this.flag = true;
	}
	public synchronized boolean keepRunning() {
		return this.flag = false;
	}
	@Override
	public void run() {
		System.out.println("Running");
		try {
			Thread.sleep(3L * 1000L);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
