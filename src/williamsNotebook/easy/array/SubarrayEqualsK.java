/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yimin Huang
 *		
 *		Given an array of integers and an integer k, you need to find the total number of contiguous subarrays whose 
 *		sum equals to k
 *
 * Algorithm Class
 */
public class SubarrayEqualsK {

	// Solution 1: naive solution
	// Time: O(n^3), Space: O(1)
	public int naiveEqualsK(int[] array, int target) {
		if(array == null) throw new IllegalArgumentException("the given array is invalid");
		int count = 0;
		for(int i = 0; i < array.length; i++) {
			for(int j = i+1; j < array.length; j++) {
				int sum = 0;
				for(int k = i; k <= j; k++) {
					sum += array[k];
				}
				if(sum == target) {
					count++;
				}
			}
		}
		return count;
	}
	// Solution 2: Time Optimization, by using prefix Sum
	// Time: O(n^2), Space: O(1)
	public int betterEqualsK(int[] array, int target) {
		if(array == null) throw new IllegalArgumentException("the given array is invalid");
		int count = 0;
		for(int i = 0; i < array.length; i++) {
			int prefixSum = array[i];
			for(int j = i + 1; j < array.length; j++) {
				prefixSum += array[j];
				if(prefixSum == target) {
					count++;
				}
			}
		}
		return count;
	}
	// Solution 3: Further optimization with time and space
	// Time: O(n), Space: O(n)
	public int bestEqualsK(int[] array, int target) {
		// sanity check
		if(array == null) throw new IllegalArgumentException("the given array is invalid");
		if(array.length == 0) return 0;
		Map<Integer, Integer> sum = new HashMap<Integer, Integer>();
		sum.put(0, 1);
		int prefixSum = 0;
		int count = 0;
		for(int i:array) {
			prefixSum += i;
			count += sum.getOrDefault(prefixSum - target, 0);
			sum.put(prefixSum, sum.getOrDefault(prefixSum, 0) + 1);
		}
		return count;
	}
	public static void main(String[] args) {
		SubarrayEqualsK solution = new SubarrayEqualsK();
		int bestEqualsK = solution.naiveEqualsK(new int[] {1,1,1}, 2);
		System.out.println(bestEqualsK);
	}
}
