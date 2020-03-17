package williamsNotebook.medium;


/**
 * @author yimin Huang
 * 
 *	Say you have an array for which the ith element is the price of a given stock on day i.
 *	Design an algorithm to find the maximum profit. You may complete as many transactions 
 *	as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *		You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *		After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 *	Example:
 *		prices = [1, 2, 3, 0, 2], maxProfit = 3, transactions = [buy, sell, cooldown, buy, sell]
 *
 * Algorithm Class
 */
public class BestTimeStock {
	
	
	
	// Situation 1:Say you have an array for which the ith element is the price of a given stock on day i.
	// If you were only permitted to complete at most k transaction (ie, buy one and sell one share of the stock),
	// design an algorithm to find the maximum profit.
	// k = Infinity (as many times as you like) and there is no any restriction
	public int maxProfitI(int[] prices) {
		if(prices == null || prices.length == 0) {
			return 0;
		}
		int profit = 0;
		for(int i = 0; i < prices.length - 1; i++) {
			// we gain every positive profit
			int diff = prices[i+1] - prices[i];
			if(diff > 0) {
				profit += diff;
			}
		}
		return profit;
	}
	
	// Follow Up 2: k = 1; and there no any restriction
	// dp[i] be the max profit between date 0 - i
	// dp[i] = max(dp[i-1], p[i]- p_min) , case 1: if you do nothing, case two, if you decide to sell
	// base case: dp[0] = 0, you can only buy rather than sell
	// corner case: when n < 2, it can not have any transaction
	public int maxProfitII(int prices[]) {
		if(prices == null || prices.length < 2) return 0;
		int p_min = prices[0];
		int[] dp = new int[prices.length];
		for(int i = 1; i < prices.length; i++) {
			p_min = Math.min(prices[1], p_min);
			dp[i] = Math.max(dp[i-1], prices[i] - p_min);
		}
		return dp[prices.length - 1];
	}
	
	// Follow Up 3: k = 2, 1-d DP, Time ~ O(3N) and there still no restriction
	// Let dp(i) be the max profit when 1st transaction is in [0, i] and 2nd transaction is in [i, N - 1].
	// dp(i) = f(i) + g(i) where f(i) = max{f(i - 1), p[i] - p_min} and g(i) = max{g(i + 1), p_max - p[i]}
	public int maxProfitIII(int[] prices) {
		if(prices == null || prices.length < 2) return 0;
		int N = prices.length;
		int[] f = new int[N];
		int[] g = new int[N];
		int min = prices[0];
		// calculate f[i]
		for(int i = 1; i < N; i++) {
			min = Math.min(min, prices[i]);
			f[i] = Math.max(f[i-1], prices[i] - min);
		}
		// calculate g[i]
		int max = prices[N-1];
		for(int i = N - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			g[i] = Math.max(g[i + 1], max - prices[i]);
		}
		// calculate the profit
		int profit = 0;
		for(int i = 0; i < N; i++) {
			profit = Math.max(profit, f[i] + g[i]);
		}
		return profit;
	}
	
	// Follow Up 4: k transactions
	// Solution: 2-d DP, Time ~ O(N^2)
	// Let d(i, j) be the max profit of i transactions in [0, j] (1 <= i <= k, 0 <= j <= N - 1).
	// Two case:
	// 		There is no sell at j: d(i, j) = d(i, j - 1)
	//		here is a sell at j (there must be a buy at l < j): d(i, j) = max_{0 <= l < j} {d(i - 1, l - 1) - p[l] + p[j]}
	// Induction rule : d(i, j) = max{d(i, j - 1), max_{1 <= l <= j} {d(i - 1, l - 1) - p[l] + p[j]}}
	//		= max{d(i, j - 1), p[j] + max_{1 <= l <= j} {d(i - 1, l - 1) - p[l]}} // move p[j] out of max
	// 	profit_max = d[k][N - 1]
	// Insight: if k > len/2, it is the same as as many transactions as you like, because you need two days to complete one transactions
	public int maxProfitIV(int k, int[] prices) {
		if(prices == null || prices.length < k) return 0;
		int profit = 0;
		if(k > prices.length/2) {
			for(int i = 0; i < prices.length - 1; i++) {
				int diff = prices[i + 1] - prices[i];
				if(diff > 0) profit += diff;
			}
			return profit;
		}
		int[][] dp = new int[k+1][prices.length];
		for(int i = 1; i <= k; i++) {
			int tmpMax = dp[i-1][0] - prices[0];
			for(int j = 1; j < prices.length; j++) {
				tmpMax = Math.max(tmpMax, dp[i-1][j-1] - prices[j]);
				dp[i][j] = Math.max(dp[i][j-1], prices[j] + tmpMax);
			}
		}
		return dp[k][prices.length - 1];
	}
	
	// Follow Up 5: if there are some restriction for the transaction
	// Assumption, the stock price could not be negative, with Cooldown
	// Method 1: BruteForce, Naive Solution, for every day, you have three choices, you can try them all
	// Time: O(3^n), Space: O(n)
	
	
	// Method 2: Dynamic Programming 
	// hold[i] = the greatest profit that holding the stock in the ith day
	// unhold[i] = the greatest profit that unholding the stock in the ith day
	// Target = unhold[n-1], meaning that you sell all stock at the last day
	// Base case:
	// hold[0] = -price[0]
	// hold[1] = max(-price[1],-price[0])
	// unhold[0] = 0;
	// Induction rule:
	// Case 1.0 for hold[i] in the ith day
	// 		1.1 you buy stock in the ith day.  unhold[i-2] - price[i]
	//  	1.2 you do not buy stock in the ith day.	hold[i-1]
	// Case 2.0 for unhold[i] in the ith day
	// 		2.1 you sale stock in the ith day	hold[i-1] + price[i]
	//		2.2 you do not sale stock in the ith day. unhold[i-1] 
	// Time: O(n), Space: O(n) can optimize to O(1)
	public int maxProfit(int[] prices) {
		// sanity check
		if(prices == null || prices.length <= 1) {
			return 0;
		}
		int n = prices.length;
		int[] hold = new int[n];
		int[] unhold = new int[n];
		// base case
		hold[0] = -prices[0];
		unhold[0] = 0;
		// induction rule
		for(int i = 1; i < n; i++) {
			if(i == 1) {
				hold[i] = Math.max(hold[i-1],-prices[i]);
			} else {
				hold[i] = Math.max(unhold[i-2] - prices[i], hold[i-1]);
			}
			unhold[i] = Math.max(hold[i-1] + prices[i], unhold[i-1]);
		}
		// greedy thought: you must not hold the stock for have greatest profit
		return unhold[n-1];
	}
	// Method 3: Space optimization, logic is exactly the same as method 2
	// Time: O(n), Space: O(n) can optimize to O(1)
	public int maxProfitOptimize(int[] prices) {
		// sanity check
		if(prices == null || prices.length <= 1) {
			return 0;
		}
		int n = prices.length;
		// base case
		int hold = -prices[0];
		int unholdTwoDaysBefore = 0;
		int unhold = 0;
		int temp;
		// induction rule
		for(int i = 1; i < n; i++) {
			if(i == 1) {
				// temp = hold[i], hold = hold[i-1]
				temp = Math.max(hold, -prices[i]);
			} else {
				temp = Math.max(unholdTwoDaysBefore - prices[i], hold);
				// update unhold[i-2] = unhold[i-1]
				unholdTwoDaysBefore = unhold;
			}
			unhold = Math.max(hold + prices[i], unhold);
			hold = temp;
		}
		// greedy thought: you must not hold the stock for have greatest profit
		return unhold;
	}
	
	public static void main(String[] args) {
		BestTimeStock solution = new BestTimeStock();
		int maxProfit = solution.maxProfitOptimize(new int[] {1, 2, 3, 0, 2});
		System.out.println(maxProfit);
	}
}
