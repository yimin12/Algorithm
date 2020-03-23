/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月21日 下午10:15:53
* Description:
* 	Given an array with integers.
* 	Find twonon-overlapping_subarrays_A_and_B, which|SUM(A) - SUM(B)|is the largest.
* 	Return the largest difference.
* Example:
* 	For[1, 2, -3, 1], return6.
*/

public class MaximumSubArrayDifference {
	
	// Key Insight: There two possible for getting the max value, first is |leftMax - rightMin|, second is |leftMin - rightMax|
	// So you need to maintain four arrays to do the calculation (leftMax, leftMin, rightMax, rightMin)
	// Time: O(n)  and Extra Space: O(4n)
	public int maxDiffSubArrays(int[] array) {
		if(array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int[] leftMax = new int[n];
		int[] leftMin = new int[n];
		int[] rightMax = new int[n];
		int[] rightMin = new int[n];
		
		leftMax[0] = array[0];
		leftMin[0] = array[0];
		rightMax[n-1] = array[n-1];
		rightMin[n-1] = array[n-1];
		
		int localMax = leftMax[0];
		int globalMax = leftMax[0];
		for(int i = 1; i < n; i++) {
			globalMax = Math.max(localMax + array[i], array[i]);
			globalMax = Math.max(localMax, globalMax);
			leftMax[i] = globalMax;
		}
		
		int localMin = leftMin[0];
		int globalMin = leftMin[0];
		for(int i = 0; i < n; i++) {
			localMin = Math.min(localMax + array[i], array[i]);
			globalMin = Math.min(localMin, globalMin);
			leftMin[i] = globalMin;
		}
		
		localMax = rightMax[n-1];
		globalMax = rightMax[n-1];
		for(int i = n - 2; i >= 0; i--) {
			localMax = Math.max(localMax + array[i], array[i]);
			globalMax = Math.max(localMax, globalMax);
			rightMax[i] = globalMax;
		}
		
		localMin = rightMin[n-1];
		globalMin = rightMin[n-1];
		for(int i = n - 2; i >= 0; i--) {
			localMin = Math.min(localMin + array[i], array[i]);
			globalMin = Math.min(localMin, globalMin);
			rightMin[i] = globalMin;
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < n - 1; i++) {
			int temp = Math.max(Math.abs(leftMax[i] - rightMin[i+1]), Math.abs(leftMin[i] - rightMax[i+1]));
			result = Math.max(temp, result);
		}
		return result;
	}
}
