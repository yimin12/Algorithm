/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 上午11:28:18
* Description:
* 	Givenn_kind of items with size Ai and value Vi(each item has an infinite number available) and a backpack with size_m. What's the maximum value can you put into the backpack?
* 	You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
* 	Given 4 items with size[2, 3, 5, 7]and value[1, 5, 2, 4], and a backpack with size10. The maximum value is15.
*/
public class BackPackIII {

	//  Key insight: logic is similar with coin change
	//  i, number of types items
	//  j, required amount of value you can not exceed
	// dp[i][j] represent the max value we can get by picking n types of items that not exceed the required amount
	// induction rule: 
	// dp[i][j] = max {dp[i - 1][j - k*A[i-1]] + k*V[i-1]}, k >= 0, k <= j / A[i-1](enumerate all possible times of item i we can pick and calculate the max)
	// base case: dp[i][0] = 0, dp[0][j] = 0
	// time : O(m*n*k) worst case, Space: O(n*m)
	public int unboundedBackPack(int[] A, int[] V, int m) {
		if(A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) return 0;
		int n = A.length;
		int[][] dp = new int[n+1][m+1];
		// base case 
		dp[0][0] = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				for(int k = 0; k <= j/A[i-1]; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j - k*A[i-1]] + k*V[i-1]);
				}
			}
		}
		return dp[n][m];
	}
	// Method 2: Time Optimization to Time: O(m*n)
	// We notice that dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i-1]] + V[i-1], dp[i-1][j-2*A[i-1]] + 2*V[i-1]..... dp[i-1][j-k*A[i-1]] + k*V[i-1]);
	//			And  dp[i][j-A[i-1] = Math.max(dp[i-1][j-A[i-1]] + V[i-1], dp[i-1][j-2*A[i-1]] + 2*V[i-1]..... dp[i-1][j-k*A[i-1]] + k*V[i-1]);
	// So dp[i][j] = Math.max(dp[i-1][j], dp[i][j-A[i-1]] + V[i-1]);
	public int unboundedBackPackII(int[] A, int[] V, int m) {
		if(A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) return 0;
		int n = A.length;
		int[][] dp = new int[n+1][m+1];
		dp[0][0] = 0;
		for(int i = 1; i <= i; i++) {
			for(int j = 1; j <= m; j++) {
				dp[i][j] = dp[i-1][j];
				if(j >= A[i-1]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-A[i-1]] + V[i-1]);
				}
			}
		}
		return dp[n][m];
	}
	// Method 3: Keep Optimized the space complexity
	public int unboundedBackPackIII(int[] A, int[] V, int m) {
		if(A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) return 0;
		int n = A.length;
		int[] dp = new int[m+1];
		dp[0] = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				dp[j] = Math.max(dp[j], j >= A[i-1] ? dp[j-A[i-1]] + V[i-1] : 0);
			}
		}
		return dp[m];
	}
}
