/**
 * 
 */
package practise.designPattern.adapter.singleton.copy;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月19日 下午9:47:42
* Description:
* 	This is also called draconionSingleton. Despite this class being thread-safe, we can see that there's a 
* clear performance drawback. Each time we want to get the instance of our singleton, we need to acquire potential
* unnecessary lock. To Fix that, we could instead start by verifying if we need to create the object in the first
* place and only in case we could acquire the lock
*/
public class LazySingleton {
	// private constructor
	private LazySingleton() {}
	public static LazySingleton instance = null;
	public synchronized LazySingleton getInstance() {
		if(instance == null) {
			this.instance = new LazySingleton();
		}
		return instance;
	}
}
