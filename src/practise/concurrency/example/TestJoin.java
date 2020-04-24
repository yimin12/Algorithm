/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午12:20:12
* Description:
*
* 	
*/
public class TestJoin {

	public static void main(String[] args) throws InterruptedException {
		TestJoin test = new TestJoin();
		Thread t = new Thread(test.new MyRunnable());
		System.out.println("start");
		t.start();
		t.join(); // let main thread start join
		System.out.println("end");
	}
	
	class MyRunnable implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("hello");
		}
		
	}
}
