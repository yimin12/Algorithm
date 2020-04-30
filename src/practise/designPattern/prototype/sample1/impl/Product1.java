/**
 * 
 */
package practise.designPattern.prototype.sample1.impl;

import practise.designPattern.prototype.sample1.ifc.Product;
import practise.designPattern.prototype.sample1.ifc.Prototype;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午3:26:22
* Description:
*/
// Product 1 implements 
public class Product1 implements Product, Prototype{
	
	private String name;

	public Product1(String name) {
		this.name = name;
	}
	
	// Copy constructor needed by clone();
	public Product1(Product1 p) {
		this.name = p.getName();
	}
	@Override
	public Product clone() {
		return new Product1(this);
	}

	@Override
	public String getName() {
		return name;
	}

}
