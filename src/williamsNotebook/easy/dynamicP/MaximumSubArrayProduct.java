/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月5日 下午5:42:01
* Description:
* 	Find the contiguous subarray within an array (containing at least one number) which has the largest product.
* Example:
* 	For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
* 
*/

public class MaximumSubArrayProduct {
	// State : max_product[i] = the max subarray product ending in i
	//		   min_product[i] = the min subarray product ending in i
	// Induction rule: max_product[i] = getMax(max_product[i-1]*num[i], min_product[i-1]*num[i])
	// 				   min_product[i] = getMin(min_product[i-1]*num[i], min_product[i-1]*num[i])
	// Base case: min_product[0] = max_product[0] = num[0]
	// Time: O(n) Space: O(n)
	public int maxProduct(int[] array) {
		if(array == null || array.length == 0) return 0;
		int[] max = new int[array.length];
		int[] min = new int[array.length];
		int result = max[0];
		for(int i = 1; i < array.length; i++) {
			if(array[i] > 0) {
				max[i] = Math.max(max[i], array[i] * max[i-1]);
				min[i] = Math.min(min[i], array[i] * min[i-1]);
			} else {
				max[i] = Math.max(max[i], array[i] * min[i-1]);
				min[i] = Math.min(min[i], array[i] * max[i-1]);
			}
			result = Math.max(max[i], result);
		}
		return result;
	}
	// Version, Space optimization
	public int maxProductOpt(int[] array) {
		if(array == null || array.length == 0) return 0;
		int result, curMax, curMin;
		result = curMax = curMin = array[0];
		for(int i = 1; i < array.length; i++) {
			int temp = curMax;
			curMax = Math.max(array[i], Math.max(curMax * array[i], curMin * array[i]));
			curMin = Math.min(array[i], Math.min(curMin * array[i], temp * array[i]));
			result = Math.max(result, curMax);
		}
		return result;
	}
	
}
