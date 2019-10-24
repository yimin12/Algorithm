/**
 * 
 */
package Common;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午1:01:25
* Description:
* 	Given a sorted integer array, remove duplicate elements. For each group of 
* 	elements with the same value keep only one of them. Do this in-place, using 
* 	the left side of the original array and maintain the relative order of the 
* 	elements of the array. Return the array after deduplication.
* Assumption:
* 	The array is not null
* Examples:
* 	{1, 2, 2, 3, 3, 3} → {1, 2, 3}
*/

public class ArrayDedupliactionI {
	public int[] dedup(int[] array) {
//		Assumption: the array is not null
		if(array.length <= 1) {
			return array;
		}
//		slow pointer starts from 0 and array[0] is always valid.
//		slow pointer is "included" in the valid partition
		int end = 0;
		for(int i = 0; i < array.length; i++) {
//			for any duplicate elements, we just ignore
			if(array[i] != array[end]) {
				array[++end] = array[i];
			}
		}
//		You can just return the length of valid subarray after dedup inplace
		return Arrays.copyOf(array, end+1);
//		why end +1 , it is the length, not the index
	}
}
