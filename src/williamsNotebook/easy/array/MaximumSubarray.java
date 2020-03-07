/**
 * 
 */
package williamsNotebook.easy.array;

/**
 * @author yimin Huang
 *		Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 *		For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 *		the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * Algorithm Class
 */
public class MaximumSubarray {
	
	// Time : O(n) and Extra Space : O(n) can be optimized to O(1)
	public int maxSubarraySum(int[] array) {
		// Sanity check
		if(array == null || array.length == 0) return 0;
		// Can optimized to O(1)
		int dp[] = new int[array.length];
		int GMax = Integer.MIN_VALUE;
		dp[0] = Math.max(0, array[0]);
		for(int i = 1; i < array.length; i++) {
			dp[i] = (dp[i-1] < 0 ? 0 : dp[i - 1]) + array[i];
			if(dp[i] > GMax) {
				GMax = dp[i];
			}
		}
		return GMax;
	}
	
	// Follow Up 1: Find the contiguous subarray within an array (containing at least one number) which has the largest product.
	// 1-d DP: Time ~ O(N), Space ~ O(1)
	public int maxSubarrayProduct(int[] array) {
		// Because it contains the negative problem, so we need to record the largest product and the smallest product.
		// Induction rule:
		// f(i) = max{f(i - 1) * A[i], A[i], g(i - 1) * A[i]}
		// g(i) = min{f(i - 1) * A[i], A[i], g(i - 1) * A[i]}
		// Use maxProd and minProd to store f(i) and g(i) at each i, respectively.
		int maxProd = 1, minProd = 1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for(int i = 0; i < array.length; i++) {
			int temp = maxProd;
			maxProd = Math.max(Math.max(maxProd * array[i], array[i]), minProd * array[i]);
			minProd = Math.min(Math.min(minProd * array[i], array[i]), temp * array[i]);
			max = Math.max(maxProd, max);
			min = Math.min(minProd, min);
		}
		return max;
	}
	
	public static void main(String[] args) {
		MaximumSubarray solution = new MaximumSubarray();
		int maxSubarraySum = solution.maxSubarraySum(new int[] {-2,1,-3,4,-1,2,1,-5,4});
		System.out.println(maxSubarraySum);
		
		int maxSubarrayProduct = solution.maxSubarrayProduct(new int[] {2,3,-2,4});
		System.out.println(maxSubarrayProduct);
	}
	
}
