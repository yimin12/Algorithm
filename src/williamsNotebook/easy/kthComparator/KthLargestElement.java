/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午5:28:31
* Description:
* 	Find K-th largest element in an array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
* 	You can swap elements in the array
* 	In array [9,3,2,4,8], the 3rd largest element is 4.
* 	In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc
*/

public class KthLargestElement {

	// Method 1: Sort Array Time: O(nlogn) Space: O(1) no extra space required
	public int findKthLargest(int[] array, int k) {
		final int N = array.length;
		Arrays.sort(array);
		return array[N-k];
	}
	// Method 2: Max Heap O(N lg K) running time + O(K) memory
	public int findKthLargestI(int[] array, int k) {
		if(array == null || array.length == 0 || k <= 0) {
			return -1;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b)-> a == b ? 0 : b - a);
		for(int i = 0; i < array.length; i++) {
			maxHeap.offer(array[i]);
		};
		for(int j = 0; j < k - 1; j++) {
			maxHeap.poll();
		}
		return maxHeap.peek();
	}
	// Method 3: Quick Select  O(N) best case / O(N^2) worst case running time + O(1) memory
	public int findKthLargestIII(int[] array, int k) {
		// Assume the given array is valid
		k = array.length - k;
		int left = 0, right = array.length - 1;
		while(left < right) {
			final int j = partition(array, left, right);
			if(j < k) {
				left = j + 1;
			} else if(j > k) {
				right = j - 1;
			} else {
				break;
			}
		}
		return array[k];
	}
	private int partition(int[] array, int left, int right) {
		int i = left, j = right + 1;
		while(true) {
			while(i < right && less(array[++i],array[left]));
			while(j > left && less(array[left], array[--j]));
			if(i >= j) break;
			exch(array, i, j);
		}
		exch(array, left, j);
		return j;
	}
	private void exch(int[] array, int i, int j) {
		final int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	private boolean less(int v, int w) {
		return v < w;
	}
	// Method 4: Use Shuffle: O(n) guaranteed running time and O(1) Extra space
	public int findKthLargestV(int[] array, int k) {
		shuffle(array);
		k = array.length - k;
		int left = 0, right = array.length - 1;
		while(left < right) {
			final int j = partition(array, left, right);
			if(j < k) {
				left = j + 1;
			} else if (j > k){
				right = j - 1;
			} else {
				break;
			}
		}
		return array[k];
	}
	private void shuffle(int[] array) {
		final Random random = new Random();
		for(int i = 1; i < array.length; i++) {
			final int r = random.nextInt(i + 1);
			exch(array, i, r);
		}
	}
	// Method 5: Select with two pointers, O(N) best case / O(N^2) worst case running time + O(1) memory
	public int kthLargestElement(int k, int[] array) {
		if(array == null || array.length == 0 || k <= 0 || k > array.length) {
			return 0;
		}
		return select(array, 0, array.length - 1, array.length - k);
	}
	public int select(int[] array, int left, int right, int k) {
		if(left == right) {
			return array[left];
		}
		int pivotIndex = partitionI(array, left, right);
		if(pivotIndex == k) {
			return array[pivotIndex];
		} else if(pivotIndex < k) {
			return select(array, pivotIndex+1, right, k);
		} else {
			return select(array, left, pivotIndex - 1, k);
		}
	}
	public int partitionI(int[] array, int left, int right) {
		int pivot = array[left];
		while(left < right) {
			while(left < right && array[right] >= pivot) {
				right--;
			}
			array[left] = array[right];
			while(left < right && array[left] <= pivot) {
				left++;
			}
			array[right] = array[left];
		}
		array[left] = pivot;
		return left;
	}
}
