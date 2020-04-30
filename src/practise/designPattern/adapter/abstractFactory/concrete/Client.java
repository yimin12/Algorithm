/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.concrete;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午3:25:53
* Description:
*/

public class Client {
	
	public static void main(String[] args) {
		System.out.println("Creating an order object");
		Factory1.createOrder();
		
		System.out.println("Creating an product object");
		Factory1.createProduct();
	}
}
