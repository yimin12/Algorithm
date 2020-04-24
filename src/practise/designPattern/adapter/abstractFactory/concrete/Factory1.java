/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.concrete;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午3:31:01
* Description:
*/

/**
 * @author 61771
 *
 */
public class Factory1 {

	public static Order createOrder() {
		System.out.println(" Create Order 1 object created!");
		return new Order1();
	}
	
	public static Product createProduct() {
		System.out.println(" Create a product object: ");
		return new Product1();
	}
}
