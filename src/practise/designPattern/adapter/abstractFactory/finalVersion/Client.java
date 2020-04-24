/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.finalVersion;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午4:12:31
* Description:
*/

public class Client {
	
	public static void main(String[] args) {
		AbstractFactory factory = AbstractFactory.getInstance("1");
		
		System.out.println("Creating a family of objects ");
		factory.createProductA();
		factory.createProductB();
		System.out.println("Creating a family of objects ");
	}
}
