/**
 * 
 */
package Basic;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����1:12:51
* Description:
* 	Given a sorted integer array, remove duplicate elements. For each group of elements with 
* 	the same value keep at most two of them. Do this in-place, using the left side of the 
* 	original array and maintain the relative order of the elements of the array. Return the 
* 	array after deduplication.
* Assumption:
* 	The given array is not null
* Examples:
* 	{1, 2, 2, 3, 3, 3} �� {1, 2, 2, 3, 3}
*/

public class ArrayDeduplicationII {
	public int[] dedup(int[] array) {
//		The array is not null
		if(array.length <= 2) return array;
		int end = 1;
		for(int i = 2; i < array.length; i++) {
//			we can do this, because this is sorted array, and array[i] == array[end - 1] implies
//			array[end-1]==array[end]==array[i].
//			if it is not a sorted array we need to find other way out
			if(array[i] != array[end-1]) {
				array[++end] = array[i];
			}
		}
		return Arrays.copyOf(array, end+1);
	}
}
