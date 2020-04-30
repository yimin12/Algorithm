/**
 * 
 */
package practise.designPattern.prototype.sample2.impl;
import practise.designPattern.prototype.sample2.itf.ProductA;
import practise.designPattern.prototype.sample2.itf.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午8:17:08
* Description:
*/
public class Client {

	private ProductA productA;
	private ProductB productB;
	private PrototypeFactory ptFactory;
	
	public Client(PrototypeFactory ptFactory) {
		this.ptFactory = ptFactory;
	}
	public String operation() {
		System.out.println("Client: Delegating object creation to prototype factory.");
		productA = ptFactory.createProductA();
		productB = ptFactory.createProductB();
		return "Hello World form " + productA.getName() + " and " + productB.getName() + " !";
	}
	
}
