/**
 * 
 */
package practise.designPattern.prototype.sample2.impl;

import practise.designPattern.prototype.sample2.itf.ProductA;
import practise.designPattern.prototype.sample2.itf.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午5:33:33
* Description:
*/

public class PrototypeFactory {

	private ProductA productA;
	private ProductB productB;
	
	public PrototypeFactory(ProductA pa, ProductB pb) {
		this.productA = pa;
		this.productB = pb;
	}
	
	public ProductA createProductA() {
		System.out.println("PrototypeFactory: Cloning a ProductA object");
		return productA.clone();
	}
	
	public ProductB createProductB() {
		System.out.println("PrototypeFactory: Cloning a ProductB object");
		return productB.clone();
	}
	
}
