/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.finalVersion;

import practise.designPattern.adapter.abstractFactory.finalVersion.impl.ProductA1;
import practise.designPattern.adapter.abstractFactory.finalVersion.impl.ProductB1;
import practise.designPattern.adapter.abstractFactory.finalVersion.intf.ProductA;
import practise.designPattern.adapter.abstractFactory.finalVersion.intf.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午4:03:54
* Description:
*/

public class Factory2 extends AbstractFactory{

	public ProductA createProductA() {
		System.out.println("creating a ProductA2 object");
		return new ProductA1();
	}

	public ProductB createProductB() {
		System.out.println("creating a ProductB2 object");
		return new ProductB1();
	}
}
