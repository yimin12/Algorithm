/**
 * 
 */
package practise.designPattern.prototype.sample2.impl;

import practise.designPattern.prototype.sample2.itf.ProductA;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午5:21:15
* Description:
*/

public class ProductA1 implements ProductA{

	private String name;
	
	public ProductA1(String name) {
		this.name = name;
	}
	
	// Copy constructor needed by clone();
	public ProductA1(ProductA1 pa) {
		this.name = pa.getName();
	}
	@Override
	public String getName() {
		return name;
	}

	public ProductA1 clone() {
		return new ProductA1(this);
	}
	
}
