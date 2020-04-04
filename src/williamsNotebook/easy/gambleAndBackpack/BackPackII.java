/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午11:57:21
* Description:
* 	Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
* 	You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
* Example:
* 	Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
*/
public class BackPackII {

	// dp[i][j] the maximum value by picking first ith items which is not exceed j weight
	// induction rule: dp[i][j]= Max(dp[i-1][j], j >= A[i-1] ? dp[i-1][j-A[i-1]] + V[i-1] : 0)
	// base case: dp[0][0] = 0;
	// Time: O(n*m) Space : O(n*m)
	public int backPack(int m, int[] A, int[] V) {
		// Assumption: all inputs are valid
		int[][] dp = new int[A.length+1][m+1];
		dp[0][0] = 0;
		for(int i = 1; i <= A.length; i++) {
			for(int j = 1; j <= m; j++) {
				if(j >= A[i-1]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i-1]] + V[i-1]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[A.length][m];
	}
	// Space Optimization for using rolling array
	// Time: O(n * m) and Space is O(min(n, m))
	public int backPackII(int m, int[] A, int[] V) {
        // maximum value of using first i items under total size j
        int[] dp = new int[m + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
            }
        }
        return dp[m];
    }
	
	public static void main(String[] args) {
		BackPackII solution = new BackPackII();
		int[] W = new int[] {2, 3, 5, 7};
		int[] V = new int[] {1, 5, 2, 4};
		int backPack = solution.backPackII(10, W, V);
		System.out.println(backPack);
	}
}
