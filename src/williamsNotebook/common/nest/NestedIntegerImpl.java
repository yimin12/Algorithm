/**
 * 
 */
package williamsNotebook.common.nest;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午7:00:33
* Description:
* 	something like [1,[4,[6]]]
* 	It contains a integer and a list together
*/

public class NestedIntegerImpl implements NestedInteger{
	
	private List<NestedInteger> list;
	private Integer integer;
	
	// initial data structure here, 3 different comparator
	public NestedIntegerImpl(List<NestedInteger> list) {
		this.list = list;
	}
	
	public NestedIntegerImpl(Integer integer) {
		this.integer = integer;
	}
	
	public NestedIntegerImpl() {
		this.list = new ArrayList<NestedInteger>();
	}
	// add number 
	public void add(NestedInteger nestedInteger) {
		if(this.list != null) {
			this.list.add(nestedInteger);
		} else {
			this.list = new ArrayList<NestedInteger>();
			list.add(nestedInteger);
		}
	}
	
	public void setInteger(int num) {
		this.integer = num;
	}

	// determine whether the nestedInteger is list or a single number
	@Override
	public boolean isInteger() {
		return this.integer != null;
	}

	@Override
	public Integer getInteger() {
		return integer;
	}

	@Override
	public List<NestedInteger> getList() {
		return list;
	}
	
	public String printNest(NestedInteger nest, StringBuilder sb) {
		if(nest.isInteger()) {
			sb.append(nest.getInteger());
			sb.append(",");
		}
		sb.append("[");
		for(NestedInteger ni : nest.getList()) {
			if(ni.isInteger()) {
				sb.append(ni.getInteger());
				sb.append(",");
			} else {
				printNest(nest, sb);
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
}
