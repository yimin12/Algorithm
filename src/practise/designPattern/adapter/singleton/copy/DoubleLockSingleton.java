/**
 * 
 */
package practise.designPattern.adapter.singleton.copy;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月19日 下午10:17:12
* Description:
* 	As for the lazy 
*/
public class DoubleLockSingleton {
	
	private DoubleLockSingleton() {
		
	}
	
	private static DoubleLockSingleton instance = null;
	
	public static DoubleLockSingleton getInstance() {
		if(instance == null) {
			DoubleLockSingleton sc;
			synchronized (DoubleLockSingleton.class) {
				sc = instance;
				if(sc == null) {
					synchronized (DoubleLockSingleton.class) {
						if(sc == null) {
							sc = new DoubleLockSingleton();
						}
					}
					instance = sc;
				}
			}
		}
		return instance;
	}
}
// Or another version
class DclSingleton{
	private static volatile DclSingleton instance;
	public static DclSingleton getInstance() {
		if(instance == null) {
			synchronized (DclSingleton.class) {
				if(instance == null) {
					instance = new DclSingleton();
				}
			}
		}
		return instance;
	}
}
