package Common;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年11月16日 下午9:42:14
* Description:
* 	Pick one element from each of the two sorted array A and B. s = a + b, where a is from A and b is from B. Among all the s, find 
* 	kth smallest one. This is classical way to use encapsulation to solve the problem
* Example:
	A = {1,2,3}
	B = {100,200}
	A[0] + B[1] = 201
	A[1] + B[0] = 102
	
	So the most smallest value is 101
*/

public class KthSmallestTwoSortedArray {
	// A, B are not null, both have at least one element
	// k > 0 && k < A.length * B.length 
	// In this solution, we encapsulate the element in A and B into new class Element
	static class Element {
		int x; // index from A
		int y; // index from B
		int sum; // sum of A[x] + B[y]
		public Element(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
		// if you do this encapsulation for using a HashMap, you need to override hashCode and equals function
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			if(!(obj instanceof Element)) return false;
			Element other = (Element)obj;
			return this.x == other.x && this.y == other.y && this.sum == other.sum;
		}
		// there are many ways to implement the function, commonly it will use constant 31 as adjustment
		@Override
		public int hashCode() {
			return this.x*31*31 + this.y * 31 + this.sum;
		}
	}
	public int kSmallest(int[] A, int[] B, int k) {
		int m = A.length;
		int n = B.length;
		Set<Element> set = new HashSet<Element>();
		// boolean[][] visited = new boolean[m][n], it could work  but it has constant space complexity O(m*n)
		PriorityQueue<Element> minHeap = new PriorityQueue<Element>(k, 
				new Comparator<Element>() {
			@Override
			public int compare(Element e1, Element e2) {
				if(e1.sum == e2.sum) {
					return 0;
				}
				return e1.sum < e2.sum ? -1:1;
			}
		});
		Element cur = new Element(0, 0, A[0]+B[0]);
		minHeap.offer(cur);
		set.add(cur);
		// visited[0][0] = true;
		// create an variable to take the result
		int result = A[0] + B[0];
		for(int i=0; i < k; i++) {
			cur = minHeap.poll();
			result = cur.sum;
			// there only two direction to go, and use set to cut the branches
			if(cur.x < m - 1) {
				Element next = new Element(cur.x + 1, cur.y, A[cur.x+1] + B[cur.y]);
				if(set.add(next)) {
					minHeap.offer(next);
				}
			}
			if(cur.y < n - 1) {
				Element next = new Element(cur.x, cur.y + 1, A[cur.x] + B[cur.y + 1]);
				if(set.add(next)) {
					minHeap.offer(next);
				}
			}
		}
		return result;
	}
	// if you use boolean[][] visited to record , Space is not very efficient O(m*n) + O(k)
	// if k << m*n, you should use HashSet, space Complexity: O(k) + O(k)
}
