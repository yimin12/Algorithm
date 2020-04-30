/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.abstractF.Impl;

import practise.designPattern.adapter.abstractFactory.abstractF.AbstractFactory;
import practise.designPattern.adapter.abstractFactory.abstractF.ProductA;
import practise.designPattern.adapter.abstractFactory.abstractF.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午1:16:42
* Description:
*/
public class Factory2 implements AbstractFactory{

	@Override
	public ProductA createProductA() {
		System.out.println("Facotry2 : Creating a Product A2 object.");
		return new ProductA2();
	}

	@Override
	public ProductB createProductB() {
		System.out.println("Factory2 : Creating a Product B2 object.");
		return new ProductB2();
	}

}
