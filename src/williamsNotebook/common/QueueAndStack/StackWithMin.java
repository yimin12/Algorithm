/**
 * 
 */
package williamsNotebook.common.QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午4:01:34
* Description:
* 	Enhance the stack implementation to support min() operation, min() should return the 
* 	current minimum value in the stack. If the stack is Empty, min() return -1;
*/
public class StackWithMin {

	private Deque<Integer> stack;
	private Deque<Integer> minStack;
	public StackWithMin() {
		stack = new LinkedList<Integer>();
		minStack = new LinkedList<Integer>();
	}
	public Integer min() {
		if(minStack.isEmpty()) {
			return null;
		}
		return minStack.peekFirst();
	}
	public void push(int value) {
		stack.offerFirst(value);
		if(minStack.isEmpty() || value <= minStack.peekFirst()) {
			minStack.offerFirst(value);
		}
	}
	public Integer pop() {
		if(stack.isEmpty()) {
			return null;
		}
		Integer result = stack.pollFirst();
		if(minStack.peekFirst().equals(result)) {
			minStack.pollFirst();
		}
		return result;
	}
	public Integer top() {
		if(stack.isEmpty()) {
			return null;
		}
		return stack.peekFirst();
	}
}
