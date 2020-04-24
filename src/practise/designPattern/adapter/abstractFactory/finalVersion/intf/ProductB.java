/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.finalVersion.intf;

import practise.designPattern.builder.interf.Product;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午3:48:20
* Description:
*/

public interface ProductB extends Product {
	
	public String createProduct();
	public String sellProduct();
	
}
