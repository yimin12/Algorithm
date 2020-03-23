/**
 * 
 */
package williamsNotebook.common.QueueAndStack;

import java.util.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午3:53:38
* Description:
* 	As the title described, you should only use two stacks to implement a queue's actions.
* 	The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.
* 	Both pop and top methods should return the value of first element.
* 
*/

public class QueueWithTwoStacks {

	// key insight, queue'removement and queue's offering are operated in different sides
	// insert into the in stack
	private LinkedList<Integer> in;
	// remove from the our stack
	private LinkedList<Integer> out;
	public QueueWithTwoStacks() {
		this.in = new LinkedList<Integer>();
		this.out = new LinkedList<Integer>();
	}
	public Integer poll() {
		move();
		return out.isEmpty() ? null : out.pollFirst();
	}
	public void offer(int val) {
		in.offerFirst(val);
	}
	public Integer peek() {
		move();
		return out.isEmpty() ? null : out.peekFirst();
	}
	private void move() {
		if(out.isEmpty()) {
			while(!in.isEmpty()) {
				out.offerFirst(in.pollFirst());
			}
		}
	}
	public int size() {
		return in.size() + out.size();
	}
	public boolean isEmpty() {
		return in.size() == 0 && out.size() == 0;
	}
}
