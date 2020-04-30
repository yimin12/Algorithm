/**
 * 
 */
package practise.designPattern.factoryMethod;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午1:26:45
* Description:
*/
public class MyApp {

	public static void main(String[] args) {
		Creator creator1 = new Creator1();
		System.out.println(creator1.operation());
	
		Creator creator2 = new Creator2();
		System.out.println(creator2.operation());
	}
}
