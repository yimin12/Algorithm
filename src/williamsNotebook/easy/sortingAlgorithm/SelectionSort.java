/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����8:06:24
* Description: Classical Selection Sort
*/

public class SelectionSort {
	
	public int[] selectionSort(int[] array) {
//		Conner Case
		if(array == null || array.length == 0) {
			return array;
		}
		for(int i = 0; i < array.length; i++) {
//			In order to record the minimum value of this time of linear scan
			int min = i;
//			find the minimum element when you linear scan the rest of the subarray
			for(int j=i + 1;j < array.length;j++) {
				if(array[j] < array[min]) {
					min = j;
				}
			}
			swap(array, i, min);
		}
		return array;
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	public static void main(String[] args) {
		SelectionSort solutSelectionSort = new SelectionSort();
		int[] array = null;
		solutSelectionSort.selectionSort(array);
		System.out.println(Arrays.toString(array));
		
		array = new int[] {1, 2, 3, 4, 5};
		solutSelectionSort.selectionSort(array);
		System.out.println(Arrays.toString(array));
		
		array = new int[] {5, 4, 3, 2 ,1};
		solutSelectionSort.selectionSort(array);
		System.out.println(Arrays.toString(array));
		
		array = new int[] {2, 4, 1, 5 ,3};
		solutSelectionSort.selectionSort(array);
		System.out.println(Arrays.toString(array));
	}
}
