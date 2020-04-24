/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午12:29:49
* Description:
*/

public class TestInterrupt {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new MyThread();
		t.start();
		Thread.sleep(1000);
		t.interrupt();
		t.join();
		System.out.println("end");
	}
}
class MyThread extends Thread{
	
	@Override
	public void run() {
		Thread hello = new HelloThread();
		hello.start();
		try {
			hello.join();
		} catch (Exception e) {
			System.out.println("interrupted");
		}
		hello.interrupt();
	}
}
class HelloThread extends Thread{
	@Override
	public void run() {
		int n = 0;
		while(!isInterrupted()) {
			n++;
			System.out.println(n + " hello!");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				break;
			}
		}
	}
}
