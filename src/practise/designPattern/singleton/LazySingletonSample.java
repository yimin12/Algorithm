/**
 * 
 */
package practise.designPattern.singleton;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午8:28:10
* Description:
*/
public class LazySingletonSample {

	public static void main(String[] args) {
		Person p = Person.getInstance();
		p.getName();
		Person p2 = Person.getInstance();
		p2.getName();
		if(p == p2) {
			System.out.println("We are the same people");
		} else {
			System.out.println("We are not the same");
		}
	}
	
}
// singleton
class Person{
	
	private static volatile Person instance = null;
	
	private Person() {};
	public static synchronized Person getInstance() {
		if(instance == null) {
			instance = new Person();
			System.out.println("Creating people");
		} else {
			System.out.println("Peopeo have been created already!");
		}
		return instance;
	}
	
	public void getName() {
		System.out.println("My Name is Yimin Huang");
	}
}
