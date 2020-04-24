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

public class Factory1 extends AbstractFactory{

	public ProductA createProductA() {
		System.out.println("creating a ProductA1 object");
		ProductA productA1 = new ProductA1();
		productA1.makeProduct();
		return new ProductA1();
	}

	public ProductB createProductB() {
		System.out.println("creating a ProductB1 object");
		ProductB productB1 = new ProductB1();
		productB1.sellProduct();
		return new ProductB1();
	}
}
