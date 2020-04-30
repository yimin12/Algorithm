/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月29日 下午4:43:46
* Description:
*/
public class TestRunnable {

	public static void main(String[] args) {
		Thread thread = new Thread(new Programmer());
		thread.start();
		for(int i= 0; i < 1000;i++) {
			System.out.println("Do you miss me?");
		}
	}
}

class Programmer implements Runnable{

	@Override
	public void run() {
		for(int i = 0 ; i < 1000 ; i++) {
			System.out.println("I love yimin");
		}
	}
	
}
