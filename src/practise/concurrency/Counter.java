/**
 * 
 */
package practise.concurrency;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月19日 下午6:29:18
* Description:
* 
* 	Test Race Conditions and Critical Sections
*/

public class Counter {
	protected long count = 0;
	public void add(long value) {
		this.count = this.count + value;
	}
}
