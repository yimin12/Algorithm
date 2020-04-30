/**
 * 
 */
package practise.concurrency;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月19日 下午4:11:31
* Description:
*  	Commonly, there are two ways to create single thread
*/
public class CreateThread extends Thread {

	// Method 1:
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass() + " is running");
	}
	public static void main(String[] args) {
		
		
		// create classified thread
		Thread myThread = new CreateThread();
		myThread.start();
		
		// anonymous inner class
		Thread thread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("another thread is running");
			}
		};
		thread.start();
		
		Runnable myRunnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("?????????????????");
				
			}
		};
		myRunnable.run();
		
		// Lambda expression
		Runnable runnable = () -> {System.out.println("Lambda Runnable running");};
		runnable.run();
		
		Thread newThread1 = new Thread(runnable);
		newThread1.run();
		
		Thread thread2 = new Thread("New MyThread") {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String threadNameString = Thread.currentThread().getName();
				System.out.println(threadNameString);
			}
		};
		thread2.start();
	}
}
