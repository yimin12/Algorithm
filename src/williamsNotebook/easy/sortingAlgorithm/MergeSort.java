/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����8:18:47
* Description: Classical Merge Sort
*/

/**
 * @author 61771
 *
 */
public class MergeSort {
	public int[] mergeSort(int[] array) {
		if(array == null) {
			return array;
		}
//		allocate helper array to help merge help, 
//		it will guarantee no more than O(n) space is used
//		In this case, space complexity is O(n)
		int[] helper = new int[array.length];
		mergeSort(array, helper, 0, array.length - 1);
		return array;
	}
//	The key idea of mergeSort is divide and conquer, this part is dividing the whole array
	private void mergeSort(int[] array, int[] helper, int left, int right) {
		if(left >= right) {
			return;
		}
		int mid = left + (right - left)/2;
		mergeSort(array,helper, left, mid);
		mergeSort(array,helper, mid + 1, right);
//		After divide the whole array, you need to merge
		merge(array,helper,left, mid, right);
	}
	private void merge(int[] array, int[] helper, int left, int mid, int right) {
//		copy the content to helper array and we can merge from the helper array
		for(int i = left; i <= right;i++) {
			helper[i] = array[i];
		}
		int leftIndex = left;
		int rightIndex = mid + 1;
		while(leftIndex <= mid && rightIndex <= right) {
			if(helper[leftIndex] < helper[rightIndex]) {
				array[left++] = helper[leftIndex++];
			} else {
				array[left++] = helper[rightIndex++];
			}
		}
//		If we still have some elements at left sides, we need to copy them
		while(leftIndex <= mid) {
			array[left++] = helper[leftIndex++];
		}
//		if there are some elements at right side, we do not need to copy them, 
//		because they are already in their position
	}
//	MergeSort with returnning new AarrayList each level
	public ArrayList<Integer> mergeSort(ArrayList<Integer> array){
		if(array == null) {
			return array;
		}
		return mergeSort(array,0, array.size()-1);
	}
	private ArrayList<Integer> mergeSort(ArrayList<Integer> array, int left, int right){
		ArrayList<Integer> result = new ArrayList<Integer>();
//		base case:
		if(left > right) return result;
		if(left == right) {
			result.add(array.get(left));
			return result;
		}
		int mid = left + (right - left)/2;
		List<Integer> reLeft = mergeSort(array, left, mid);
		List<Integer> reRight = mergeSort(array, mid+1, right);
		return result;
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
