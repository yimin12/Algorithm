/**
 * 
 */
package williamsNotebook.common.QueueAndStack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午10:33:43
* Description:
* 	Design a max stack that supports push, pop, top, peekMax and popMax.
		push(x) -- Push element x onto stack.
		pop() -- Remove the element on top of the stack and return it.
		top() -- Get the element on the top.
		peekMax() -- Retrieve the maximum element in the stack.
		popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
*/
// Method 1: Regular method with two stacks
public class StackWithMax {
	Deque<Integer> stack;
	Deque<Integer> maxStack;
	public StackWithMax() {
		stack = new LinkedList<Integer>();
		maxStack = new LinkedList<Integer>();
	}
	public void push(int x) {
		int max = maxStack.isEmpty() ? x : maxStack.peek();
		maxStack.push(max > x ? max : x);
		stack.push(x);
	}
	public int pop() {
		maxStack.pop();
		return stack.pop();
	}
	public int top() {
		return stack.peek();
	}
	public int peekMax() {
		return maxStack.peek();
	}
	public int popMax() {
		int max = peekMax();
		Deque<Integer> buffer = new LinkedList<Integer>();
		while(top() != max) {
			buffer.push(pop());
		}
		pop();
		while(!buffer.isEmpty()) push(buffer.pop());
		return max;
	}
}
// Method 2: Double LinkedList + TreeMap
class MaxStack{
	static class Node{
		int val;
		Node prev, next;
		public Node(int v) {val = v;}
	}
	static class DoubleLinkedList{
		Node head, tail;
		public DoubleLinkedList() {
			head = new Node(0);
			tail = new Node(0);
			head.next = tail;
			tail.prev = head;
		}
		public Node add(int val) {
			Node x = new Node(val);
			x.next = tail;
			x.prev = tail.prev;
			tail.prev = tail.prev.next;
			return x;
		}
		public int pop() {
			return unlink(tail.prev).val; 
		}
		public int peek() {
			return tail.prev.val;
		}
		public Node unlink(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			return node;
		}
	}
	TreeMap<Integer, List<Node>> map;
	DoubleLinkedList dll;
	public MaxStack() {
		this.map = new TreeMap<Integer, List<Node>>();
		this.dll = new DoubleLinkedList();
	}
	public void push(int val) {
		Node node = dll.add(val);
		if(!map.containsKey(val)) {
			map.put(val, new ArrayList<Node>());
		}
		map.get(val).add(node);
	}
	public int pop() {
		int val = dll.pop();
		List<Node> l = map.get(val);
		l.remove(l.size() - 1);
		if(l.isEmpty()) map.remove(val);
		return val;
	}
	public int top() {
		return dll.peek();
	}
	public int peekMax() {
		return map.lastKey();
	}
	public int popMax() {
		int max = peekMax();
		List<Node> l = map.get(max);
		Node node = l.remove(l.size() - 1);
		dll.unlink(node);
		if(l.isEmpty()) map.remove(max);
		return max;
	}
}
