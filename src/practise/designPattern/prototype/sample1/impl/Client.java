/**
 * 
 */
package practise.designPattern.prototype.sample1.impl;

import practise.designPattern.prototype.sample1.ifc.Product;
import practise.designPattern.prototype.sample1.ifc.Prototype;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午3:30:14
* Description:
*/
public class Client {

	private Product product;
	private Prototype prototype;
	
	public Client(Prototype prototype) {
		this.prototype = prototype;
	}
	
	public String operation() {
		product = prototype.clone();
		return "Client: Cloning " + prototype.getClass().getSimpleName() + ".\n" + product.getName() + " object copied.";
	}
}
