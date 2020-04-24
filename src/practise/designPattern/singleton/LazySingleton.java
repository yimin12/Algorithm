/**
 * 
 */
package practise.designPattern.singleton;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午8:20:01
* Description:
*/

public class LazySingleton {
	
	private static volatile LazySingleton instance = null; // guarantee every thread can watch the instance
	
	private LazySingleton() {
		// private constructor, avoid being created by outside world
	}
	
	public static synchronized LazySingleton getInstance() {
		if(instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
	
}
