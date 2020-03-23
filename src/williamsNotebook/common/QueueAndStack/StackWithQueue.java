package williamsNotebook.common.QueueAndStack;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午4:08:25
* Description:
* 	Implement the following operations of a stack using queues.
*       push(x) -- Push element x onto stack.
		pop() -- Removes the element on top of the stack.
		top() -- Get the top element.
		empty() -- Return whether the stack is empty.
*/
public class StackWithQueue {

	private Queue<Integer> queue;
	public StackWithQueue() {
		queue = new LinkedList<Integer>();
	}
	// it might not be very efficient 
	// Time: O(n ^ 2) and space is O(n)
	public void push(int val) {
		queue.add(val);
		for(int i = 1; i < queue.size(); i++) {
			queue.add(queue.poll());
		}
	}
	public int pop() {
		return queue.poll();
	}
	public int top() {
		return queue.peek();
	}
	public boolean empty() {
		return queue.isEmpty();
	}
}
