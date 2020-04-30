/**
 * 
 */
package practise.designPattern.adaptorI.sample2;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午8:51:38
* Description:
*/

public class Adapter implements Strategy{

	private Adaptee adaptee;
	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public String algorithm() {
		return adaptee.specificAlgorithm();
	}
}
