/**
 * 
 */
package practise.designPattern.singleton;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午8:25:46
* Description:
*/
public class EagerSingleton {
	
	private EagerSingleton() {}
	private static final EagerSingleton instance = new EagerSingleton();
	
	public static synchronized EagerSingleton getInstance() {
		return instance;
	}
}
