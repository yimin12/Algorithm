/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午9:44:40
* Description:
*/
public class MyCounter {

	long count = 0;
	public synchronized void add(long value) {
		this.count += value;
	}
	public void print() {
		System.out.println(count);
	}
}
