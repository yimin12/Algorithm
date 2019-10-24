/**
 * 
 */
package Common;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午4:44:52
* Description:
* 	Use the least number of comparisons to get the largest and 2nd largest number in 
* 	the given integer array. Return the largest number and 2nd largest number.
* Assumption:
* 	The given array is not null and has length of at least 2
* Examples:
* 	{2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
*/

public class LargeAndSecondLarge {
//	The element class will be used to store the original value in the array and all values compared to it
	static class Element{
		int value;
		List<Integer> compredValues;
		
		Element(int value){
			this.value = value;
			this.compredValues = new ArrayList<Integer>();
		}
	}
	public int[] largestAndSecond(int[] array) {
//		array is not null, array.length >= 2, and convert the original array to element array
		Element[] helper = convert(array);
//		largerLength is the left partition's length containing the larger values after each round
//		of comparison
//		for each round, the comparison is still doing for each of the indices pairs(i, largerLength-1-i)
//		So that the larger element are always on the left side, and the largerLength will be cut in half
//		each round. largetLength is obviously initiated by the array's length;
		int largerLength = array.length;
		while(largerLength > 1) {
			compareAndSwap(helper, largerLength);
			largerLength = (largerLength+1)/2;
		}
		return new int[] {helper[0].value, largest(helper[0].compredValues)};
	}
	private Element[] convert(int[] array) {
		Element[] helper = new Element[array.length];
		for(int i = 0; i < array.length; i++) {
			helper[i] = new Element(array[i]);
		}
		return helper;
	}
//	Compare each of the indices pairs (i, largerLength - 1 - i), swap the larger values on the left side
//	if necessary, and put the smaller value into the larger value's compared value list
	private void compareAndSwap(Element[] helper, int largerLength) {
		for(int i = 0; i < largerLength/2; i++) {
			if(helper[i].value < helper[largerLength-1-i].value) {
				swap(helper, i, largerLength-1-i);
			}
			helper[i].compredValues.add(helper[largerLength-1-i].value);
		}
	}
	private void swap(Element[] helper, int left, int right) {
		Element temp = helper[left];
		helper[left] = helper[right];
		helper[right] = temp;
	}
	private int largest(List<Integer> list) {
		int max = list.get(0);
		for(int num : list) {
			max = Math.max(max, num);
		}
		return max;
	}
}
