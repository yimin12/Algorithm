/**
 * 
 */
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
