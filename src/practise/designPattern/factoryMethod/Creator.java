/**
 * 
 */
package practise.designPattern.factoryMethod;

import practise.designPattern.factoryMethod.interf.Product;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午1:31:25
* Description:
*/
public abstract class Creator {

	private Product product;
	public abstract Product factoryMethod(); // set the dynamically method to abstract class
	
	public String operation() {
		product = factoryMethod();
		return "Hello world from " + this.getClass().getSimpleName() + "!\n" + product.getName() + " created.";
	}
	
}
