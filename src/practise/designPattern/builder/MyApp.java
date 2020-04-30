/**
 * 
 */
package practise.designPattern.builder;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午5:02:22
* Description:
*/
public class MyApp {

	public static void main(String[] args) {
		Director director = new Director(new Builder1());
		System.out.println(director.construct());
	}
}
