/**
 * 
 */
package practise.designPattern.builder;

import practise.designPattern.builder.interf.Builder;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午4:58:10
* Description:
*/
public class Director {

	private ComplexObject co;
	private Builder builder;
	
	public Director(Builder builder) {
		this.builder = builder;
	}
	public String construct() {
		System.out.println("Director : Delegating constructing " + "a complex object to a builder object");
		builder.buildPartA();
		builder.buildPartB();
		co = builder.getResult();
		return "Hello Word from" + co.getParts() + " objects!";
	}
}
