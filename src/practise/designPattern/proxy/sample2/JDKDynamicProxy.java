/**
 * 
 */
package practise.designPattern.proxy.sample2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月24日 下午6:11:20
* Description:
*/

public class JDKDynamicProxy {

	private static int a = 4, b = 2;
	
	public static Object createJDKProxy(Arithmetic real) {
		Object proxyArithmetic = Proxy.newProxyInstance(real.getClass().getClassLoader(), real.getClass().getInterfaces(), new JdkDPQueryHandler(real));
		return proxyArithmetic;
	}
	
	public static void main(String[] args) {
		Arithmetic real = new Arithmetic();
		Object proxyArithmetic = createJDKProxy(real);
		int add = ((AddInterface)proxyArithmetic).add(a, b);
		int sub = ((SubInterface)proxyArithmetic).sub(a, b);
		System.out.println(add + " and " + sub);
	}
	
}

// prepare handler, similar as the proxy in static proxy
class JdkDPQueryHandler implements InvocationHandler{
	private Arithmetic real;

	public JdkDPQueryHandler(Arithmetic real) {
		this.real = real;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String m = method.getName();
		System.out.println(m);
		System.out.println("The method " + m + " starting with : parameter " + Arrays.asList(args));
		Object object = method.invoke(real, args);
		System.out.println("Ending....");
		return object;
	}
}

// abstract subject
interface AddInterface{
	int add(int a, int b);
}

interface SubInterface{
	int sub(int a, int b);
}


// real subject
class Arithmetic implements AddInterface, SubInterface{

	@Override
	public int sub(int a, int b) {
		return a - b;
	}

	@Override
	public int add(int a, int b) {
		return a + b;
	}
}