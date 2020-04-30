/**
 * 
 */
package practise.designPattern.factoryMethod;

import practise.designPattern.factoryMethod.impl.Product1;
import practise.designPattern.factoryMethod.interf.Product;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午2:17:08
* Description:
*/
public class Creator1 extends Creator{
	
	@Override
	public Product factoryMethod() {
		return new Product1();
	}
}
