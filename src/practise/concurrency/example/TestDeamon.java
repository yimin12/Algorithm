/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午1:02:12
* Description:
*/
public class TestDeamon {

	public static void main(String[] args) {
		Thread t = new Thread1();
		// it guarantee two threads finish together
		t.setDaemon(true);
		t.start();
		System.out.println("main: wait 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			
		}
		System.out.println("main: end!");
	}
}
class Thread1 extends Thread {
	
	public void run() {
		for(;;) {
			System.out.println("Thread-1: running....");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
	
			}
		}
	}
}
