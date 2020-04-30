/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月29日 下午5:37:02
* Description:
*	MAX_PRIORITY  10
   	NORM_PRIORITY 5 (Ä¬ÈÏ)
   	MIN_PRIORITY  1
   
   	setPriority()
   	getPriority()
*/

public class TestPriority {

	public static void main(String[] args) throws Exception {
		MyThread1 it = new MyThread1();
		Thread thread1 = new Thread(it);
		MyThread1 it2 = new MyThread1();
		Thread thread2 = new Thread(it2);
		
		thread1.setPriority(Thread.MIN_PRIORITY);
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread1.start();
		thread2.start();
		Thread.sleep(100);
		it.stop();
		it2.stop();
	}
}

class MyThread1 implements Runnable{

	private boolean flag = true;
	private int num = 0; 
	@Override
	public void run() {
		while(flag) {
			System.out.println(Thread.currentThread().getName() + "-->" + num++);
		}		
	}
	public void stop() {
		this.flag = false;
	}
}