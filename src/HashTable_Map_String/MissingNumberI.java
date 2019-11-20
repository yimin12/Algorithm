/**
 * 
 */
package HashTable_Map_String;

import java.util.HashSet;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 下午1:08:12
* Description: In the sequential array, there are one missing number, find it
* 
*/

public class MissingNumberI {
//	Method 1: use HashSet
//	Assumption: array is not null
	public int missingI(int[] array) {
		int n = array.length + 1; // It suppose to be contains this number of value
		HashSet<Integer> set = new HashSet<Integer>();
		for(int number: array) {
			set.add(number);
		}
		for(int i = 0; i < n; i++) {
			if(!set.contains(i)) {
				return i;
			}
		}
		return n;
	}
//	Method 2:use sum
	public int missingII(int[] array) {
		int n = array.length +1;
//		数列求和
		long targetSum = (n+0L) * (n+1)/2;
		long actualSum = 0L;
		for(int num : array) {
			actualSum += num;
		}
		return (int)(targetSum - actualSum);
	}
	
//	Method 3 : bit operation O(n)
	public int missingIII(int[] array) {
		int n = array.length + 1;
		int xor = 0;
//		xor 1 to n
		for(int i = 0; i <= n; i++) {
			xor ^= i;
		}
//		after this operation, all the numbers from 1 to n are pair xor'ed except for the missing number
		for(int num : array) {
			xor ^= num;
		}
		return xor;
	}
//	Method 4 :swap to the original position O(n)
	public int missingIV(int[] array) {
//		try to swap the numbers to its corresponding position
//		for the number x, the corresponding position is x - 1
//		in order to sort it in ascending order
		for(int i = 0; i < array.length; i++) {
			while(array[i] != i + 1 && array[i] != array.length + 1) {
				swap(array, i, array[i-1]);
			}
		}
//		if the missing number is in range of 1 - n - 1
		for (int i = 0; i < array.length; i++) {
			if(array[i]!= i + 1) {
				return i + 1;
			}
		}
		return array.length+ 1;
	}
	private void swap(int array[], int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
