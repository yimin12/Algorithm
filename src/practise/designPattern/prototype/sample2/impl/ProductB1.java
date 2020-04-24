/**
 * 
 */
package practise.designPattern.prototype.sample2.impl;

import practise.designPattern.prototype.sample2.itf.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午5:25:58
* Description:
*/

/**
 * @author 61771
 *
 */
public class ProductB1 implements ProductB{

	private String name;
	public ProductB1(String name) {
		this.name = name;
	}
	// Copy constructor need by clone();
	public ProductB1(ProductB1 pa) {
		this.name = pa.getName();
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public ProductB clone() {
		return new ProductB1(this);
	}
	
}
