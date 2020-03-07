/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashSet;
import williamsNotebook.common.SWAP;

/**
 * @author yimin Huang
 * 
 *	Given an unsorted integer array, find the first missing positive integer.
 *
 *	For example,
 *		Given [1,2,0] return 3
 *		and [3,4,-1,1] return 2
 *	Your algorithm should run in O(n) time and uses constant space.
 *	
 * Algorithm Class
 */
public class FirstMissingPositive {

	// Follow Up 1: Missing number 
	// Method 1: use hash set, Time: O(n) extra space: O(n)
	public int missingI(int[] array) {
		int n = array.length + 1;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i : array) {
			set.add(i);
		}
		for(int i = 1; i <= n; i++) {
			if(!set.contains(i)) {
				return i;
			}
		}
		return n;
	}
	// Method 2: use sum , Time: O(n) extra space: O(1)
	public int missingII(int[] array) {
		int n = array.length + 1;
		// the supposed sum of consecutive ascending array
		long sum = (n+0L)*(n+1)/2;
		long act = 0L;
		for(int i:array) {
			act += i;
		}
		return (int)(sum - act);
	}
	// Method 3: bit operation, Time: O(n) extra space: O(1)
	public int missingIII(int[] array) {
		int n = array.length + 1;
		int xor = 0;
		for(int i = 1; i <= n; i++) {
			xor ^= i;
		}
		for(int i:array) {
			xor ^= i;
		}
		return xor;
	}
	// Method 4: swap to original position, Time: O(n) extra space: O(1)
	public int missingIV(int[] array) {
		for(int i = 0; i < array.length; i++) {
			while(array[i] != i + 1 && array[i] != array.length + 1) {
				SWAP.intSwap(array, i,  array[i] - 1);
			}
		}
		for(int i = 0; i < array.length; i++) {
			if(array[i] != i + 1) {
				return i + 1;
			}
		}
		return array.length + 1;
	}
	
	// Follow Up 2: find the first missing positive, similar idea with Follow Up 1, method 4.
	public int firstMissingPositive(int[] array) {
		int i = 0; 
		while(i < array.length) {
			// two edge case
			// 	case 1: array[i] == array[i-1] will have dead loop
			// 	case 2:	array[i] > array.length, will cause out of boundary exception	
			if(array[i] > 0 && array[i] <= array.length && array[i] != array[array[i] - 1]) {
				SWAP.intSwap(array, i, array[i]-1);
			} else {
				i++;
			}
		}
		for(i = 0; i < array.length; i++) {
			if(array[i] != i + 1) {
				return i + 1;
			}
		}
		return i + 1;
	}
}
