/**
 * 
 */
package practise.designPattern.prototype.sample2;

import practise.designPattern.prototype.sample2.impl.Client;
import practise.designPattern.prototype.sample2.impl.ProductA1;
import practise.designPattern.prototype.sample2.impl.ProductB1;
import practise.designPattern.prototype.sample2.impl.PrototypeFactory;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午5:16:35
* Description:
*/
public class MyApp {

	public static void main(String[] args) {
		Client client = new Client(new PrototypeFactory(new ProductA1("ProductA1"), new ProductB1("ProductB1")));
		System.out.println(client.operation());
	}
}
