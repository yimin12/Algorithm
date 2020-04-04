/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午4:11:05
* Description:
* 	Assume that you have n yuan. There are many kinds of rice in the supermarket. Each kind of rice is bagged and must 
* 	be purchased in the whole bag. Given the weight, price and quantity of each type of rice, find the maximum weight 
* 	of rice that you can purchase.
* Example:
* 	Given: prices = [2,4] weight = [100,100] amounts = [4,2], n = 8
* 	Return:400
*/

public class BackPackVII {

	// Bounded KnapSack Problem (BKP)
	// 	多重背包问题 ：这个问题与 0-1 背包的区别在于，0-1 背包中每种物品有且只有一个，而多重背包中一种物品有nums[i] 个。
	// dp[i][j] represent take first i types of items, the maximum value we can get by not exceeding j 
	// dp[i][j] = max(dp[i - 1][j - k * price[i - 1]] + k * weight[i - 1]), k: 0 ~ amounts if j - k * price[i - 1]
	// base case: dp[0][j] = 0; dp[i][0] = 0;
	// Time: O(m * n * k) where m is total value, n is the number of options, and k is amount of each option
	// Space: O(n * m);
	public int boundedBackPack(int[] prices, int[] weight, int[] amounts, int m) {
		// Sanity Check
		if(prices == null || weight == null || amounts == null || prices.length * weight.length * amounts.length == 0) {
			return 0;
		}
		// Assume that length of three given array is equal
		int n = prices.length;
		int[][] dp = new int[n+1][m+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				dp[i][j] = dp[i-1][j];
				for(int k = 1; k <= amounts[i-1]; k++) {
					dp[i][j] = Math.max(dp[i][j], (j >= k*prices[i-1] ? dp[i-1][j-k*prices[i-1]] + k*weight[i-1] : 0));
				}
			}
		}
		return dp[n][m];
	}
	// Can not optimize the time, but it can be optimized by space： 
	// if there is another constrains (like k), we should iterate first when you try to optimize space
	public int backPackII(int n, int[] prices, int[] weight, int[] amounts) {
        int all = prices.length;
        int[] f = new int [n + 1];
        for (int i = 1; i <= all; i++) {
            for (int k = 1; k <= amounts[i - 1]; k++) {
                for (int j = n; j >= prices[i - 1]; j--) {
                    f[j] = Math.max(f[j], f[j - prices[i - 1]] + weight[i - 1]);
                }
            }
        }
        return f[n];
    }
	public static void main(String[] args) {
		BackPackVII solution = new BackPackVII();
		int[] prices = {2,4}, weight = {100,100}, amounts = {4,2};
		int backPackII = solution.backPackII(8, prices, weight, amounts);
		System.out.println(backPackII);
	}
	
}
