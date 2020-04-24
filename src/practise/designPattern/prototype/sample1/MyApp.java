/**
 * 
 */
package practise.designPattern.prototype.sample1;
import practise.designPattern.prototype.sample1.impl.Client;
import practise.designPattern.prototype.sample1.impl.Product1;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午3:21:42
* Description:
*/

public class MyApp {
	public static void main(String[] args) {
		// creating a client object
		Client client = new Client(new Product1("Product1"));
		// CallingConvention an Operation on the client;
		System.out.println(client.operation());
	}
}
