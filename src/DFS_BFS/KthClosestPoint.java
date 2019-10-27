/**
 * 
 */
package DFS_BFS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月24日 上午11:46:57
* Description:
* 	Given three arrays sorted in ascending order. Pull one number from each array to form 
* 	a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.
* 	We are using euclidean distance here.
* Assumption:
* 	The three given arrays are not null or empty, containing only non-negative numbers
* 	K >= 1 and K <= a.length * b.length * c.length
* Return: 
* 	a size 3 integer list, the first element should be from the first array, the second element should be from
*   the second array and the third should be from the third array
* Examples:
* 	A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
* 	The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
* 	The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
*/

public class KthClosestPoint {
//	Assumptions: a,b,c are not null and length >= 1, k >=1 and k <= a.length*b.length*c.length
//	we will need a min heap, with comparator to compare the distance
//	Note that we are using the index in a, b, c as values in the List<Integer>/
	public List<Integer> closest(final int[] a, final int[] b, final int[] c,  int k){
		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<List<Integer>>(2*k, 
				new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				long d1 = distance(o1,a,b,c);
				long d2 = distance(o2,a,b,c);
				if(d1==d2) {
					return 0;
				}
				return d1 < d2 ? -1:1;
			}
		});
//		Node that List's equals() method has already overridden, and it is comparing the actual elements in the List
		Set<List<Integer>> visited = new HashSet<List<Integer>>();
//		Initial state is <0,0,0>, meaning picking the smallest elements from three arrays
		List<Integer> cur = Arrays.asList(0,0,0);
		visited.add(cur);
		minHeap.offer(cur);
		while(k > 0) {
			cur = minHeap.poll();
			List<Integer> n = Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));
			if(n.get(0) < a.length && visited.add(n)) {
				minHeap.offer(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
			if(n.get(1) < b.length && visited.add(n)) {
				minHeap.offer(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
			if(n.get(2) < c.length && visited.add(n)) {
				minHeap.offer(n);
			}
			k--;
		}
//		at last, we replace the index with actual value in a,b,c
		cur.set(0, a[cur.get(0)]);
		cur.set(1, b[cur.get(1)]);
		cur.set(2, c[cur.get(2)]);
		return cur;
	}
	private long distance(List<Integer> point, int[] a, int[] b, int[] c) {
		long dis = 0;
		dis += a[point.get(0)] * a[point.get(0)];
		dis += b[point.get(1)] * b[point.get(1)];
		dis += c[point.get(2)] * c[point.get(2)];
		return dis;
	}
}
