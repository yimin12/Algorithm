/**
 * 
 */
package QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月9日 上午10:46:04
* Description:
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
//		when value <= current min value in stack, you need to push the value to minStack
		if(minStack.isEmpty() || value <= minStack.peekFirst()) {
			minStack.offerFirst(value);
		}
	}
	
	public Integer pop() {
		if(stack.isEmpty()) {
			return null;
		}
		Integer result = stack.pollFirst();
		// when the pop value is the same as top value of minStack, the value need to be 
		// popped from minStack as well;
		if(minStack.peekFirst().equals(result)) {
//			means that the current minimum has been popped
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
