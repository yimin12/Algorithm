/**
 * 
 */
package Common;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午4:16:26
* Description:
* 	Given an unsorted integer array, remove adjacent duplicate elements repeatedly, 
* 	from left to right. For each group of elements with the same value do not keep any of them.	
	Do this in-place, using the left side of the original array. 
	Return the array after deduplication.
  Assumption:
  	The given array is not null
  Examples:
  	{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
*/

public class ArrayDeduplicationIV {
	private int[] dedup(int[] array) {
		int end = -1;
//		an important trick of denote the empty of imitated stack, is that end = -1;
		for(int i = 0; i < array.length; i++) {
//			we are using the left part of the original array as a stack. and the top element's
//			index is end, if the stack is empty(end == -1), we just push the element into the stack
//			or if the element is not the same as the top element of the stack, we can push 
//			the element into the stack as well
			if(end == -1 || array[end] != array[i]) {
				array[++end] = array[i];
			} else {
//			otherwise, we ignore all consecutive duplicates and remove the top element of the stack
				while(i+1<array.length && array[i+1] == array[end]) {
					i++;
				}
				end--;
			}
		}
		return Arrays.copyOf(array, end+1);
	}
}
