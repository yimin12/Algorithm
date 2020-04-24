/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.abstractF.Impl;

import practise.designPattern.adapter.abstractFactory.abstractF.AbstractFactory;
import practise.designPattern.adapter.abstractFactory.abstractF.ProductA;
import practise.designPattern.adapter.abstractFactory.abstractF.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午1:11:05
* Description:
*/

public class Factory1 implements AbstractFactory{

	@Override
	public ProductA createProductA() {
		System.out.println("Factory1 : Creating a ProductA1 object");
		return new ProductA1();
	}

	@Override
	public ProductB createProductB() {
		System.out.println("Factory1 : Creating a ProductB1 object");
		return new ProductB1();
	}
	
	
}
