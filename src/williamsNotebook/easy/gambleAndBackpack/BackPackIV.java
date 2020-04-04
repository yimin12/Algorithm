/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午12:32:49
* Description:
* 	Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target 
* 	denotes the size of a backpack. Find the number of possible fill the backpack
*	Each item may be chosen unlimited number of times
* Example: Given candidate items[2,3,6,7]and target7,
* 	A solution set is: 
* 	[7], [2, 2, 3]
*/
public class BackPackIV {

	// Key Insight: Logic is exactly the logic in coin change
	// 2D array
	// dp[i][j] : use i items to fullfill the backpack
	// induction rule : dp[i][j] += dp[i-1][j-k*A[i-1];
	// base case: dp[i][0] = 1 (already fullfill) dp[0][j] = 0, can not be fullfill
	public int unboundedBackPack(int[] A, int m) {
		if(A == null || A.length == 0) return 0;
		int n = A.length;
		int[][] dp = new int[n+1][m+1];
		// base case:
		for(int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				dp[i][j] = dp[i-1][j]; // should initialize state of picking non of it
				for(int k = 1; k * A[i-1] <= j; k++) {
					dp[i][j] += dp[i-1][j-k*A[i-1]];
				}
			}
		}
		return dp[n][m];
	}
	// Method 2: Time Optimization
	// dp[i][j] = sum(dp[i - 1][j - k * A[i - 1]; (k = 0, 1, ..., j / A[i - 1]))
	// dp[i][j-A[i-1]] = sum(dp[i-1][j-k*A[i-1]); (k = 1......j / A[i-1]) 
	public int unboundedBackPackII(int[] A, int m) {
		if(A == null || A.length == 0) return 0;
		int n = A.length;
		int[][] dp = new int[n+1][m+1];
		// base case:
		for(int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				dp[i][j] += dp[i-1][j] + (j >= A[i-1] ? dp[i][j-A[i-1]] : 0);
			}
		}
		return dp[n][m];
	}
	// Method 3: Space Opt
	// We only care about the value on the left of i and on the up side of i
	public int unboundedBackPackIII(int[] A, int m) {
		if(A == null || A.length == 0) return 0;
		int n = A.length;
		int[] dp = new int[m+1];
		dp[0] = 1;
		for(int i = 1; i <= n; i++) {
			for(int j = A[i-1]; j <= m; j++) {
				dp[j] += dp[j - A[i-1]]; // dp[i][j] = dp[i-1][j] + dp[i][j-A[i-1]], dp[j] = dp[j] + dp[j-A[i-1]];
			}
		}
		return dp[m];
	}
	public static void main(String[] args) {
		BackPackIV solution = new BackPackIV();
		int[] A = {2,3,6,7};
		int unboundedBackPack = solution.unboundedBackPackIII(A, 7);
		System.out.println(unboundedBackPack);
	}
}
