/**
 * 
 */
package practise.designPattern.factoryMethod;

import practise.designPattern.factoryMethod.impl.Product2;
import practise.designPattern.factoryMethod.interf.Product;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午2:46:51
* Description:
*/

/**
 * @author 61771
 *
 */
public class Creator2 extends Creator {

	@Override
	public Product factoryMethod() {
		return new Product2();
	}

}
