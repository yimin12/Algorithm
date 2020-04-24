/**
 * 
 */
package practise.designPattern.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import practise.designPattern.builder.interf.Product;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月20日 下午4:43:12
* Description:
*/
public class ComplexObject {

	private List<Product> children = new ArrayList<Product>();
	
	public String getParts() {
		Iterator<Product> iterator = children.iterator();
		String string = "Complex Object made up of";
		while(iterator.hasNext()) {
			string += iterator.next().getName();
		}
		return string;
	}
	
	public boolean add(Product child) {
		return children.add(child);
	}
	
	public Iterator<Product> iterator(){
		return children.iterator();
	}
}
