/**
 * 
 */
package practise.designPattern.adaptorI.sample1;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午8:44:03
* Description:
*/
public class Client {

	public static void main(String[] args) {
		// creating an object adaptor and configuring it with an Adaptee object
		Target objectAdaptor = new ObjectAdaptor(new Adaptee());
		System.out.println("(1) Object Adaptor: " + objectAdaptor.operation());
		
		// create a class adaptor
		Target classAdaptor = new ClassAdapterAdaptee();
		System.out.println("(2) Class Adapter: " + classAdaptor.operation());
	}
}
