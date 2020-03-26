/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午11:37:04
* Description:
* 	We have a list ofpoints on the plane. Find theKclosest points to the origin(0, 0). 
* 	(Here, the distance between two points on a plane is the Euclidean distance.) 
*  	You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)
* Examples:
* 	Input: points = [[1,3],[-2,2]], K = 1  Output: [[-2,2]]
* 	The distance between (1, 3) and the origin is sqrt(10).
* 	The distance between (-2, 2) and the origin is sqrt(8).
* 
*/

public class KthClosestPoint {

	// Use Max Heap
	// Time: O(k + (n-k)logk);
	public int[][] kClosest(int[][] points, int k){
		if(points == null || points.length == 0 || points[0].length == 0) {
			return new int[0][0];
		}
		PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a,b)->(a[1]*a[1] + a[0]*a[0]) - (b[1]*b[1] + b[0]*b[0]));
		for(int[] p:points) {
			maxHeap.offer(p);
			if(maxHeap.size()>k) {
				maxHeap.poll();
			}
		}
		// it 
		int size = Math.min(k, maxHeap.size());
		int[][] res = new int[size][2];
		while(!maxHeap.isEmpty()) {
			res[--size] = maxHeap.poll();
		}
		return res;
	}
	// Use Min Heap
	// O(k + (n-k)log(n-k))
	public List<List<Integer>> topK(List<List<Integer>> input, int n, int m){
		if(input == null || input.size() == 0) return new ArrayList<List<Integer>>();
		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<List<Integer>>((e1, e2)-> (e1.get(0)*e1.get(0) + e1.get(1)*e1.get(1)) - (e2.get(0)*e2.get(0) + e2.get(1)*e2.get(1)));
		for(List<Integer> point : input) {
			minHeap.offer(point);
		}
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(int i = 0; i < m &&  i < n; i++) {
			res.add(minHeap.poll());
		}
		return res;
	}
	
}
