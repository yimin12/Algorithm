/**
 * 
 */
package williamsNotebook.common.nest;

import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午6:55:45
* Description:
*  something like [1,[4,[6]]] 
*  It contains a integer and a list together
*/

public interface NestedInteger {
	
	 // @return true if this NestedInteger holds a single integer, rather than a nested list.
	public boolean isInteger();
	// @return the single integer that this NestedInteger holds, if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();
	//  @return the nested list that this NestedInteger holds, if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}
