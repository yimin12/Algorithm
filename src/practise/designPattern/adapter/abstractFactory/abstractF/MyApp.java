/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.abstractF;

import practise.designPattern.adapter.abstractFactory.abstractF.Impl.Client;
import practise.designPattern.adapter.abstractFactory.abstractF.Impl.Factory1;
import practise.designPattern.adapter.abstractFactory.abstractF.Impl.Factory2;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午1:02:58
* Description:
*/
public class MyApp {

	public static void main(String[] args) {
		// Create Client object and configuring it with a factory object
		Client client = new Client(new Factory2());
		System.out.println(client.operation());
	}
}
