/**
 * 
 */
package williamsNotebook.common.QueueAndStack;

import java.util.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��9�� ����10:15:45
* Description: Imitate a queue by using two stack
*/

/**
 * @author 61771
 *
 */
public class QueueByTwoStack {
	
//	we always insert into the in stack;
	private LinkedList<Integer> in;
//	we always remove from the out stack;
	private LinkedList<Integer> out;
	
	public QueueByTwoStack() {
		in = new LinkedList<Integer>();
		out = new LinkedList<Integer>();
	}
	
	public Integer poll() {
//		if out stack is empty, need to move element from in stack to out stack;
		move();
		return out.isEmpty() ? null:out.pollFirst();
	}
	
	public void offer(int value) {
//		always put into the in stack
		in.offerFirst(value);
	}
	
	public Integer peek() {
//		In this Operation, you need ensure all the element of in stack has pushed to the out stack
		move();
		return out.isEmpty() ? null:out.peekFirst();
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
