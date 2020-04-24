/**
 * 
 */
package practise.designPattern.prototype;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午9:00:20
* Description:
* 	原型模式的克隆分为浅克隆和深克隆，Java 中的 Object 类提供了浅克隆的 clone() 方法，具体原型类只要实现 Cloneable 
* 	接口就可实现对象的浅克隆，这里的 Cloneable 接口就是抽象原型类
*/

public class CloneType implements Cloneable{

	CloneType(){
		System.out.println("prototype created");
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		System.out.println("copy prototype successed");
		return (CloneType)super.clone();
	}
}
