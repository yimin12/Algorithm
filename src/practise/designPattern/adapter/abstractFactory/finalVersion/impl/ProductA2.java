/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.finalVersion.impl;

import practise.designPattern.adapter.abstractFactory.finalVersion.intf.ProductA;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午3:50:41
* Description:
*/
public class ProductA2 implements ProductA{

	@Override
	public String makeProduct() {
		System.out.println("We are making ProductA2");
		return "";
	}

	@Override
	public String sellProduct() {
		System.out.println("We are selling ProductA2");
		return "";
	}
	
}
