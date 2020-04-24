/**
 * 
 */
package practise.designPattern.adaptorI.sample1;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午8:41:16
* Description:
*/

public class ClassAdapterAdaptee extends Adaptee implements Target{

	@Override
	public String operation() {
		// Implementing the target interface in terms of (by inheriting from) the Adaptee class.
		return specificOperation();
	}

}
