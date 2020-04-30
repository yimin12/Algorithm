/**
 * 
 */
package practise.designPattern.adapter.singleton;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月19日 下午9:08:58
* Description:
* 	Eager instantiation
*/

public class EagerSingleton {
	
	// step 1: Private constructor
	private EagerSingleton() {
		// can not be detected from outside
	}
	// Step 2: create instance immediately
	private static EagerSingleton instance = new EagerSingleton();
	
	// did not implement synchronize
	public static EagerSingleton getInstance() {
		return instance;
	}
	
}
