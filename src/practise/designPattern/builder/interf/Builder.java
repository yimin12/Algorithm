/**
 * 
 */
package practise.designPattern.builder.interf;

import practise.designPattern.builder.ComplexObject;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午4:42:19
* Description:
*/
public interface Builder {

	void buildPartA();
	void buildPartB();
	ComplexObject getResult();
}
