/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月4日 下午2:19:06
* Description:
* 	There are n coins in a line, and value of i-th coin is values[i]
* 	Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.
* 	Could you please decide the first player will win or lose?
* 	Input: [3, 2, 2]	Output: true	Explanation: The first player takes 3 at first. Then they both take 2.
*/
public class CoinsInALineIII {

	// Analysis:
	// 	设定 dp[i][j] 表示当前剩余硬币的区间为 [i, j] 时, 此时该拿硬币的人能获取的最大值.
	// 	注意, dp[i][j] 并没有包含角色信息, dp[0][values.length - 1] 表示的是先手的人能获得的最大值, 而 dp[1][values.length -1] 表示的则是后手的人能获得的最大值. 需要这样做是因为: 两个人都会采用最优策略.
	// 	当前的人的决策就是拿左边还是拿右边, 而下一个人仍然会最优决策, 所以应该是最小值中取最大值:
	//	dp[i][j] = max(	                                     	// 	取max表示当前的人选用最优策略		
	//  				min(dp[i + 2][j], dp[i + 1][j - 1]) + values[i], // 取min表示下一个人选用最优策略
    //					min(dp[i][j - 2], dp[i + 1][j - 1]) + values[j])
	//	几个边界:
	//		i > j : dp[i][j] = 0
	//		i == j : dp[i][j] = values[i]
	//		i + 1 == j : dp[i][j] = max(values[i], values[j])
	// 	关于转移方程的一点解释：
	//	当你要计算正在抽dp[i][j]的人获得最大值，你要预判对面会怎么选择(对面会在下一回合再次轮到你时抽到最小的值)，预判完别人的选择之后，比较自己能获得哪些数，然后求最大值
	// 	比如你在考虑dp[i][j]抽i时，你的对手会考虑在dp[i+1][j]这个范围里面怎么让你获得的值最少，也就是对手分别尝试拿两头算你能拿的最少值，min(dp[i+2][j],dp[i+1][j])，dp[i+2][j],dp[i+1][j]是你下回合到你时能获得的
	//	同理可得，在考虑dp[i][j]抽j时，你的对手会考虑在dp[i][j-1]这个范围里面让你获得的值最少，也就是对手分别尝试拿两头算你能拿的最小值，min(dp[i+1][j-1],dp[i][j-2]), dp[i+1][j-1],dp[i][j-2]是你下回合到你时能获得的
	//	综上两种选择，在你预判到对手会怎么选之后，你把你当前值，和自己将要获得值加起来，那种情况更多，则选择哪种情况。
	// 	以上便是 dp[i][j] = max(min(dp[i + 2][j], dp[i + 1][j - 1]) + values[i],min(dp[i][j - 2], dp[i + 1][j - 1]) + values[j])的由来
	//	判断条件，只要你能获得总价值一半以上的价值， 你就能获胜。
	// Method 1: Memory Search
	public boolean firstWillWin(int[] values) {
		if(values == null || values.length == 0) {
			return false;
		}
		int n = values.length;
		int[][] dp = new int[n+1][n+1];
		boolean[][] flag = new boolean[n+1][n+1];
		int sum = 0;
		for(int now : values) {
			sum += now;
		}
		return sum < 2 * MemorySearch(0, values.length - 1, dp, flag, values);
	}
	private int MemorySearch(int left, int right, int[][] dp, boolean[][] flag, int[] values) {
		// Avoid filing the form with multiple times
		if(flag[left][right]) {
			return dp[left][right];
		}
		flag[left][right] = true;
		// base case
		if(left > right) {
			dp[left][right] = 0;
		} else if(left == right) {
			dp[left][right] = values[left];
		} else if(left + 1 == right) {
			dp[left][right] = Math.max(values[left], values[right]);
		} else {
			// Induction rule
			int pickLeft = Math.min(MemorySearch(left+2, right, dp, flag, values), MemorySearch(left+1, right-1, dp, flag, values)) + values[left];
			int pickRight = Math.min(MemorySearch(left+1, right-1, dp, flag, values), MemorySearch(left, right-2, dp, flag, values)) + values[right];
			dp[left][right] = Math.max(pickLeft, pickRight);
		}
		return dp[left][right];
	}
	// Method 2: Pure Dynamic Programming
	// Idea is the same as CoinsInLinesII, the condition change to pick one element in a time
	public boolean firstWillWinDP(int[] values) {
        int n = values.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; ++i)
            sum[i] = sum[i - 1] + values[i - 1];
        // s[i][j] = sum[j + 1] - sum[i];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i)
            dp[i][i] = values[i];
        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i < n; ++i) {
                int j = i + len - 1;
                if (j >= n)
                    continue;
                // Small trick by using prefix Sum;
                int s = sum[j + 1] - sum[i];
                dp[i][j] = Math.max(s - dp[i + 1][j], s - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > sum[n] / 2;
    }
}
