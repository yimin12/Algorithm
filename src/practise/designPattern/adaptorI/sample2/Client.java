/**
 * 
 */
package practise.designPattern.adaptorI.sample2;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午8:46:53
* Description:
*/

public class Client {

	public static void main(String[] args) {
		Strategy strategy = new Strategy1();
		System.out.println("(1) " + strategy.algorithm());
		
		strategy = new Adapter(new Adaptee());
		System.out.println("(2) " + strategy.algorithm());
	}
}
