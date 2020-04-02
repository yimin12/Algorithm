/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����3:15:48
* Description:\
* 	Given a sorted integer array, remove duplicate elements. For each group of 
* 	elements with the same value do not keep any of them. Do this in-place, using the 
* 	left side of the original array and and maintain the relative order of the elements 
* 	of the array. Return the array after deduplication.
* Assumption:
* 	the given array is not null
* Examples:
* 	1, 2, 2, 3, 3, 3} �� {1}
*/


public class ArrayDeduplicationIII {
	public int[] dedup(int[] array) {
//		array is not null
		if(array == null || array.length <= 1) {
			return array;
		}
		int end = 0;
//		use flag to see if there is any duplicates of array[end];
		boolean flag = false;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == array[end]) {
//				if there is duplicate , set flag and do nothing
				flag = true;
			} else if(flag == true) {
//				if array[i] != array[end] and flag is set, array[end] shoudl not be included in 
//				the valid sub array, we can just replace it with array[i] since next we are going 
//				to check if htere is any duplicate of array[i]
				array[end] = array[i];
				flag = false;
			} else {
//				if array[i]!=array[end] and flag is not set, it means there is no duplicate of 
//				array[end] and it should be included in the valid subarray
				array[++end] = array[i];
			}
		}
//		do not forget that we need to check if there is any duplicates for the last array[end]
		return Arrays.copyOf(array, flag?end:end+1);
	}
}
