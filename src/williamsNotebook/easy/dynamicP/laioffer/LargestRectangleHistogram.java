/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

import java.util.Deque;
import java.util.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��24�� ����1:13:31
* Description:
* 	Given a non-negative integer array representing the heights of a list of adjacent bars. 
* 	Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in 
* 	the histogram.
* Assumptions:
* 	The given array is not null or empty
* Examples:
* 	{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9
* 	(starting from index 2 and ending at index 4)
*/
public class LargestRectangleHistogram {
	public int largest(int[] array) {
//		Assumption: array is not null and length >= 1, all the values in the array are non-negative
		int result = 0;
//		Note that the stack contains the "index", not the value of the array
		Deque<Integer> stack  = new LinkedList<Integer>();
		for(int i = 0; i <= array.length; i++) {
			int cur = i == array.length ? 0 : array[i];
			while(!stack.isEmpty()&& array[stack.peekFirst()] >= cur) {
				int height = array[stack.pollFirst()];
//				determine the left boundary of the largest rectangle with height array[i];
				int left = stack.isEmpty() ? 0 : stack.peekFirst()+1;
//				determine the right boundary of the largest rectangle with height of the poped element
				result = Math.max(result, height*(i-left));
			}
			stack.offerFirst(i);
		}
		return result;
	}
}
