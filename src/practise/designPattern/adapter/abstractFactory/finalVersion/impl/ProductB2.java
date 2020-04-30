/**
 * 
 */
package practise.designPattern.adapter.abstractFactory.finalVersion.impl;

import practise.designPattern.adapter.abstractFactory.finalVersion.intf.ProductB;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午3:54:07
* Description:
*/

public class ProductB2 implements ProductB{

	@Override
	public String createProduct() {
		System.out.println("We are creating ProductB2");
		return "";
	}

	@Override
	public String sellProduct() {
		System.out.println("We are selling ProductB2");
		return "";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
