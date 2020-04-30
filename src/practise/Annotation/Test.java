/**
 * 
 */
package practise.Annotation;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午5:58:57
* Description:
*/

public class Test {

	public static void main(String[] args) {
		Person p1 = new Person("Bob", "Beijing", 20);
		Person p2 = new Person("", "Shanghai", 20);
		Person p3 = new Person("Alice", "Shanghai", 199);
		for(Person person : new Person[] {p1,p2,p3}) {
			try {
				Person.check(person);
				System.out.println("Person " + person + " checked ok");
			} catch (Exception e) {
				System.out.println("Person " + person + "checked failed: " + e);
			}
		}
	}
}
