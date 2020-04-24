/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午9:45:21
* Description:
*/

public class CounterThread extends Thread{

	protected MyCounter counter = null;
	
	public CounterThread(MyCounter counter) {
		this.counter = counter;
	}
	
	public void run() {
		for(int i = 0 ; i < 10; i++) {
			counter.add(i);
			counter.print();
		}
	}
}
