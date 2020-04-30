/**
 * 
 */
package practise.designPattern.adaptorI.sample1;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午8:37:00
* Description:
*/

public class ObjectAdaptor implements Target{
	
	private Adaptee adaptee;
	
	public ObjectAdaptor(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public String operation() {
		return adaptee.specificOperation();
	}

}
