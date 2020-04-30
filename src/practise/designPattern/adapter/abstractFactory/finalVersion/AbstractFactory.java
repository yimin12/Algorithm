/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.finalVersion;

import practise.designPattern.adapter.abstractFactory.finalVersion.intf.ProductA;
import practise.designPattern.adapter.abstractFactory.finalVersion.intf.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午3:57:47
* Description:
*/

/**
 * @author 61771
 *
 */
public abstract class AbstractFactory {
	// Implement as Singleton
	private static AbstractFactory factory;
	public static final AbstractFactory getInstance(String string) {
		if(factory == null) {
			if(string == "1") {
				factory = new Factory1();
			} else if(string == "2") {
				factory = new Factory2();
			}
		}
		return factory;
	}
	public abstract ProductA createProductA();
	public abstract ProductB createProductB();
}
