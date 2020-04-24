/**
 * 
 */
package practise.designPattern.builder;

import practise.designPattern.adapter.abstractFactory.finalVersion.impl.ProductB1;
import practise.designPattern.builder.Impl.ProductA1;
import practise.designPattern.builder.interf.Builder;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午4:49:47
* Description:
*/
public class Builder1 implements Builder{

	private ComplexObject co = new ComplexObject();
	
	@Override
	public void buildPartA() {
		System.out.println("Builder1 : Creating and assembling ProductA1");
		co.add(new ProductA1());
	}

	@Override
	public void buildPartB() {
		System.out.println("Builder1 : Creating and assembling ProductB1");
		co.add(new ProductB1());
	}

	@Override
	public ComplexObject getResult() {
		return co;
	}

	
}
