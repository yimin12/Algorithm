/**
 * 
 */
package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author yiminH-mail:hymlaucs@gmail.com
 * @version Create Time：2020年4月13日 下午8:40:16 Description:
 */

public class Super {
	
	public static void main(String[] args) {
		Pair<Number> p1 = new Pair<>(12.3, 3.56);
		Pair<Integer> p2 = new Pair<>(123, 456);
		setSame(p1, 100);
		setSame(p2, 200);
		System.out.println(p1.getFirst() + " , " + p1.getSecond());
		System.out.println(p2.getFirst() + " , " + p2.getSecond());
	}
	static void setSame(Pair<? super Integer> p, Integer n) {
		p.setFirst(n);
		p.setSecond(n);
	}
}
class Pair<T>{
	private T first;
	private T second;
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	/**
	 * @return the first
	 */
	public T getFirst() {
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(T first) {
		this.first = first;
	}
	/**
	 * @return the second
	 */
	public T getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(T second) {
		this.second = second;
	}
	
	
}
