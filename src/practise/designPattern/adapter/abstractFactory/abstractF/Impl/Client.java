/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.abstractF.Impl;

import practise.designPattern.adapter.abstractFactory.abstractF.AbstractFactory;
import practise.designPattern.adapter.abstractFactory.abstractF.ProductA;
import practise.designPattern.adapter.abstractFactory.abstractF.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午1:19:50
* Description:
*/
public class Client {
	private ProductA productA;
	private ProductB productB;
	private AbstractFactory factory;
	public Client(AbstractFactory factory) {
		this.factory = factory;
	}
	public String operation() {
		System.out.println("Client : Delegating creating objects to a factory object");
		productA = factory.createProductA();
		productB = factory.createProductB();
		return "Hello World from " + productA.getName() + " and " + productB.getName() + "!";
	}
}
