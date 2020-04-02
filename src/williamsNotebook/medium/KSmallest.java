/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��11�� ����9:48:03
* Description: 
* 	Find the samllest k elements in unsorted array.
* Assumption:
* 	1) array is not full 
* 	2) k >= 0 and k <= array.length
*/


public class KSmallest {
	
//	method 1 : K sized max heap
	public int[] kSmallest(int[] array, int k) {
//		handle all possible conner case at the very beginning.
		if(array.length == 0 || k == 0) {
//			return an empty list
			return new int[0];
		}
		PriorityQueue<Integer> max = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
//				do not use "==" here
				if(o1.equals(o2)) {
					return 0;
				}
//				nature order is descending, you need to reverse the order
				return o1 > o2 ? -1:1;
			}
		});
		for(int i = 0; i < array.length; i++) {
//			step 1: push elements to max heap until it is full
			if(i < k) {
//				if you want to utilize heapify(), the only thing you can do is to call 
//				a certain consturctor of PriorityQueue
				max.offer(array[i]);
			} else if(array[i] < max.peek()) {
//				for the other elements, only offer it into the max heap if it is smaller
//				than the max value in the max heap
				max.poll();
				max.offer(array[i]);
			}
		}
		int[] result = new int[k];
		for(int i = k - 1; i >= 0; i--) {
			result[i] = max.poll();
		}
		return result;
	}
	
//	Method 2: quick select
	public int[] kSmallestII(int[] array, int k) {
		if(array.length == 0|| k == 0) {
			return new int[0];
		}
//		quickselect to find the kth smallest element
//		after calling this method, the first k elements in the array 
//		are the k smallest one(but not sorted)
		quickSelect(array, 0, array.length - 1, k);
		int[] result = Arrays.copyOf(array, k);
		Arrays.sort(result);
		return result;
	}
	private void quickSelect(int[] array, int left, int right, int target) {
//		Similar with quick sort, we can do partition using pivot value
		int mid = partition(array, left, right);
//		unlike quicksort, we only need to do quicksort on at most one partition
//		if the pivot is already the kth smallest element, directly return
		if(mid == target) {
			return;
		} else if(target < mid) {
//			only need to recursively call quick select on the left partition
//			if the kth smallest one is in the left partition.
			quickSelect(array, left, mid-1, target);
		} else {
			quickSelect(array, mid+1, right, target);
		}
	}
	private int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int start = left;
		int end = right - 1;
		while(start <= end) {
			if(array[start] < pivot) {
				start++;
			} else if (array[end] > pivot) {
				end--;
			} else {
				swap(array, start++, end--);
			}
		}
		swap(array, start, right);
		return start;
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
