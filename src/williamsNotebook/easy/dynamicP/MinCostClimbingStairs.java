/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午12:50:55
* Description:
* 	On a staircase, thei-th step has some non-negative costcost[i]assigned (0 indexed).
* 	Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, 
* 	and you can either start from the step with index 0, or the step with index 1.
* Example:
* 	Input:  cost = [10, 15, 20]  Output: 15
* Explanation:	 Cheapest is start on cost[1], pay that cost and go to the top.
* 	Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]  Output: 6
* Explanation:	 Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
*/
public class MinCostClimbingStairs {

	// dp[i] : The min cost to reach i 
	// Induction rule : dp[i] = Math.min(dp[i-1], d[i-2]) + cost[i]
	// Time: O(n) Space: O(n)
	public int minCostClimbingStairs(int[] cost) {
		if(cost == null || cost.length == 0) return -1;
		int len = cost.length;
		int[] climbCost = new int[len];
		climbCost[0] = cost[0];
		climbCost[1] = cost[1];
		for(int i = 2; i < len; i++) {
			climbCost[i] = Math.min(climbCost[i-1], climbCost[i-2]) + cost[i];
		}
		return Math.min(climbCost[len-1], climbCost[len-2]);
	}
	// Space Optimization
	public int minCostClimbingStairsII(int[] cost) {
		int prevOne = 0, prevTwo = 0;
		for(int i = cost.length - 1; i >= 0; i--) {
			int cur = cost[i] + Math.min(prevOne, prevTwo);
			// advance it by one step
			prevTwo = prevOne;
			prevOne = cur;
		}
		return Math.min(prevOne, prevTwo);
	}
}
