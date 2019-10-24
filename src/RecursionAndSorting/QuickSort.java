/**
 * 
 */
package RecursionAndSorting;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月8日 下午8:59:36
* Description:Classical Quick Sort
*/

/**
 * @author 61771
 *
 */
public class QuickSort {
	
	public int[] quickSort(int[] array) {
		if(array == null || array.length == 0) return array;
		quickSort(array, 0, array.length-1);
		return array;
	}
	
	private void quickSort(int[] array, int left, int right) {
		if(left >= right) return;
//		define a pivot and use the pivot to partition the array
		int pivotPos = partition(array, left, right);
//		pivot is already at its position, when we do the recursive call on two partitions,
//		pivot should not be included in any of them
		quickSort(array, left, pivotPos - 1);
		quickSort(array, pivotPos +1, right);
	}
	
	private int partition(int[] array, int left, int right) {
		int pivotIndex = pivotIndex(left, right);
		int pivot = array[pivotIndex];
//		when you pick an pivot, you should swap to the rightmost of the array
		swap(array, pivotIndex, right);
		int leftBound = left;
		int rightBound = right - 1;
		while(leftBound <= rightBound) {
			if(array[leftBound] < pivot) {
				leftBound++;
			} else if (array[rightBound] >= pivot) {
				rightBound++;
			} else {
				swap(array, leftBound++, rightBound++);
			}
		}
		swap(array,leftBound, rightBound);
		return leftBound;
	}
//	define a random way to pick the pivot
	private int pivotIndex(int left, int right) {
		return left + (int)(Math.random()*(right - left + 1));
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void main(String[] args) {
		MergeSort mg = new MergeSort();
		int[] array = null;
		mg.mergeSort(array);
		System.out.println(Arrays.toString(array));
		
		array = new int[] {1, 2, 3, 4, 5};
		mg.mergeSort(array);
		System.out.println(Arrays.toString(array));
		
		array = new int[] {5, 4, 3, 2 ,1};
		mg.mergeSort(array);
		System.out.println(Arrays.toString(array));
		
		array = new int[] {2, 4, 1, 5 ,3};
		mg.mergeSort(array);
		System.out.println(Arrays.toString(array));
	}
}
