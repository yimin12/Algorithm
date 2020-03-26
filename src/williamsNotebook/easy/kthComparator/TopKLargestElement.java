/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午3:09:03
* Description:
* 	Given an integer array, find the top k largest numbers in it.
* 	Given [3,10,1000,-99,4,100] and k = 3. Return [1000, 100, 10].
* 
* 	
*/

public class TopKLargestElement {

	// Max Heap: Time(n + klogn)  Space: O(n)
	public int[] topkLargest(int[] array, int k) {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 < o2) {
					return 1;
				} else if(o1 > o2) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();
		for(int i : array) {
			maxHeap.add(i);
		}
		int[] res = new int[k];
		for(int i = 0; i < k; i++) {
			res[i] = maxHeap.poll();
		}
		return res;
	}
	// Min Heap: Time: O((n-k)logk + k)  Space: O(k), require the result sorted in descending order
	public int[] topkLargestII(int[] array, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // default order is minHeap
		for(int i = 0; i < array.length; i++) {
			if(i < k) {
				minHeap.offer(array[i]);
			} else if(array[i] > minHeap.peek()){
				minHeap.poll();
				minHeap.offer(array[i]);
			}
		}
		int[] res = new int[k];
		for(int i = k-1; k >= 0; i--) {
			res[i] = minHeap.poll();
		}
		return res;
	}
	// Regular Sorting: Assume there no duplicate
	public int[] topkLargestIII(int[] array, int k) {
		Arrays.sort(array);
		int[] res = new int[k];
		int j = 0;
		for(int i = array.length - 1; i > array.length - k - 1; i--) {
			res[j] = array[i];
			j++;
		}
		return res;
	}
}
