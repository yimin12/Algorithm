/**
 * 
 */
package practise.designPattern.proxy.sample1;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月24日 下午6:02:42
* Description:
*/
public class Client {

	public static void main(String[] args) {
		Proxy proxy = new Proxy();
		proxy.Request();
	}
}

// abstract subject
interface Subject{
	void Request();
}

// read subject
class RealSubject implements Subject{

	@Override
	public void Request() {
		System.out.println("visited the real subject's method");
	}
}

// proxy
class Proxy implements Subject{

	private RealSubject real;
	@Override
	public void Request() {
		// lazy singleton
		if(real == null) {
			real = new RealSubject();
		}
		// AOP programming
		preRequest(); // before hand
		real.Request();
		postRequest(); // after hand
	}
	
	public void preRequest() {
		System.out.println("before accessing the real subject");
	}
	public void postRequest() {
		System.out.println("after accessing the real subject");
	}
}