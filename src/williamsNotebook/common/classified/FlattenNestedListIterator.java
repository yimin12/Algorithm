/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午4:35:50
* Description:
* 	Given a nested list of integers, implement an iterator to flatten it.
* 	Each element is either an integer, or a list -- whose elements may also be integers or other lists.
* 	Input: [[1,1],2,[1,1]]
* 	Output: [1,1,2,1,1]
*/
// Method 1: Stack 
// Similar to Nested List Weight Sum, we can use recursion to solve it. 
// But since we need to access each NestedInteger at a time, we will use a stack to help.
// In the constructor, we push all the nestedList into the stack from back to front, so when we pop the stack, it returns the very 
// first element.
public class FlattenNestedListIterator implements Iterator<Integer>{

	private Deque<NestedInteger> stack;
	
	public FlattenNestedListIterator(List<NestedInteger> nestedList) {
		stack = new ArrayDeque<NestedInteger>();
		flattenList(nestedList);
	}
	@Override
	public boolean hasNext() {
		while(!stack.isEmpty()) {
			if(stack.peek().isInteger()) return true;
			flattenList(stack.pop().getList());
		}
		return false;
	}
	@Override
	public Integer next() {
		return hasNext() ? stack.pop().getInteger() : null;
	}
	private void flattenList(List<NestedInteger> list) {
		// add the element from the end to the front to maintain the popping sequence
		for(int i = list.size() - 1; i >= 0; i--) {
			stack.offerFirst(list.get(i));
		}
	}
}
// Method 2: Using Queue to storing the elements - recursive
class NestedIterator implements Iterator<Integer>{

	private Queue<Integer> queue = new LinkedList<Integer>();
	public NestedIterator(List<NestedInteger> nestedList) {
		helper(nestedList);
	}
	
	private void helper(List<NestedInteger> list) {
		if(list == null) return;
		for(NestedInteger i : list) {
			if(i.isInteger()) queue.offer(i.getInteger());
			else helper(i.getList());
		}
		
	}
	@Override
	public boolean hasNext() {
		if(!queue.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Integer next() {
		if(hasNext()) {
			return queue.poll();
		} else {
			return -1;
		}
	}
	
}





class NestedInteger{
	
	public Integer getInteger() {
		return 0;
	}
	
	public boolean isInteger() {
		return true;
	}
	
	public List<NestedInteger> getList(){
		return new ArrayList<NestedInteger>();
	}
}
