/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.Arrays;


/**
 * @author yimin Huang
 *		
 *		You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest
 *		number of coins that you need to make up that amount. If that amount of money can not be made up by 
 *		combination of the coins, return -1;
 *	Example:
 *		coins = [1,2,5], amount = 11; 	return 3 (11 = 5+5+1)
 *		coins = [2], amount = 3; return -1'
 * Algorithm Class
 */
public class CoinChange {

	// Assumption: you may assume that you have infinite number of each kind of coin
	// 2d dynamic program:
	// n = number of types of coins
	// m = desired amount;
	// dp[i][j] = represent min number of coins, need to make up amount j with only coins[i....n-1]
	// induction rule:
	// dp[i][j] = min(dp[i+1][j], f[i+1][j-coins[i]]+1, f[i+1][j-coins[i]*2]+2 .....) (use coins[i] k times where k is 0 to j/coins[i])
	// Generalized:
	// dp[i][j] = min(dp[i+1][j-k*coins[i]] + k), 0<=k<=j/coins[i];
	// base case:
	// dp[n][0] = 0;
	// dp[n][1...m] = infinity
	// Solution 1: Naive dynamic Programming  Time:O(n*m^2) and O(m*n) extra space
	public int coinChange(int[] coins, int amount) {
		// sanity check: 
		if(coins == null || coins.length == 0 || amount < 0) throw new IllegalArgumentException("The given parameter is invalid");
		int[][] dp = new int[coins.length + 1][amount + 1];
		Arrays.fill(dp[coins.length], Integer.MAX_VALUE);
		dp[coins.length][0] = 0;
		for(int i = coins.length - 1; i >= 0; i--) {
			for(int j = 0; j <= amount; j++) {
				dp[i][j] = dp[i+1][j];
				int maxK = j / coins[i];
				for(int k = 1; k <= maxK; k++) {
					int prev = dp[i+1][j-k*coins[i]];
					// should care about the problem of overflow.
					if(prev < Integer.MAX_VALUE) {
						dp[i][j] = Integer.min(dp[i][j], prev + k);
					}
				}
			}
		}
		System.out.println(Arrays.deepToString(dp));
		return dp[0][amount];
	}
	// Solution 2: Optimization for Time complexity when you notice 
	// dp[i][j] = min(dp[i+1][j], dp[i+1][j-coins[i]]+1, dp[i+1][j-coins[i]*2]+2 .....) 
	// dp[i][j-coins[i]] = min( dp[i+1][j-coins[i]], dp[i+1][j-coins[i]*2]+1 .....)+1
	// Time:O(n*m) and O(m*n) extra space
	public int coinChangeOptimize(int[] coins, int amount) {
		if(coins == null || coins.length == 0 || amount < 0) throw new IllegalArgumentException("The given parameter is invalid");
		int[][] dp = new int[coins.length + 1][amount + 1];
		Arrays.fill(dp[coins.length], Integer.MAX_VALUE);
		dp[coins.length][0] = 0;
		for(int i = coins.length - 1; i >= 0; i--) {
			for(int j = 0; j <= amount; j++) {
				dp[i][j] = dp[i+1][j];
				if(j >= coins[i]) {
					int prev = dp[i][j-coins[i]];
					if(prev < Integer.MAX_VALUE) {
						dp[i][j] = Integer.min(dp[i][j], prev + 1);
					}
				}
			}
		}
		return dp[0][amount];
	}
	// Solution 3: Further Optimization for space complexity 
	// Time:O(n*m) and O(m) extra space
	public int coinChangeDPBest(int[] coins, int amount) {
		if(coins == null || coins.length == 0 || amount < 0) throw new IllegalArgumentException("The given parameter is invalid");
		int[] dp = new int[amount+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = coins.length - 1; i >= 0; i--) {
			for(int j = 0; j <= amount; j++) {
				if(j >= coins[i]) {
					int prev = dp[j-coins[i]];
					if(prev < Integer.MAX_VALUE) {
						dp[j] = Integer.min(dp[j], prev+1);
					}
				}
				
			}
		}
		return dp[amount];
	}
	// Solution 4: dfs with pruning and greedy thought (choose the value as large as possible)
	// Time: O(nlogn) for merge sort and O(3^n) for traverse 
	public int coinChangeDFSGreedy(int[] coins, int amount) {
		if(coins == null || coins.length == 0 || amount < 0) throw new IllegalArgumentException("The given parameter is invalid");
		// the given coins should be sorted for greedy 
		sortDescending(coins);
		int[] res = new int[] {Integer.MAX_VALUE};
		helperDFS(amount, coins, 0, 0, res) ;
		return res[0] == Integer.MAX_VALUE ? -1 : res[0];
	}
	private void helperDFS(int amount, int[] coins, int index, int count, int[] res) {
		// base case, case 1: if there only one type of coins left, 
		if(index == coins.length - 1) {
			if(amount % coins[coins.length - 1] == 0) {
				res[0] = Integer.min(res[0], count + amount/coins[coins.length - 1]);
			}
		} else {
			// case 2: we use one type of coins in each level
			for(int k = amount / coins[index]; k >= 0 && count + k < res[0]; k--) {
				helperDFS(amount - k * coins[index], coins, index + 1, count + k, res);
			}
		}
	}
	
	private void sortDescending(int[] array) {
		// practice merge sort
		int[] helper = new int[array.length];
		mergeSort(array, helper, 0, array.length-1);
		return;
	}
	private void mergeSort(int[] array, int[] helper, int left, int right) {
		if(left >= right) {
			return;
		}
		int mid = left + (right - left)/2;
		mergeSort(array, helper, left, mid);
		mergeSort(array, helper, mid+1, right);
		merge(array, helper, left, mid, right);
	}
	private void merge(int[] array, int[] helper, int left, int mid, int right) {
		// copy the content to helper array and merge from the helper array
		for(int i = left; i <= right; i++) {
			helper[i] = array[i];
		}
		int leftIndex = left, rightIndex = mid+1;
		while(leftIndex <= mid && rightIndex <= right) {
			if(helper[leftIndex] >= helper[rightIndex]) {
				array[left++] = helper[leftIndex++];
			} else {
				array[left++] = helper[rightIndex++];
			}
		}
		// post processing: if there still have some elements at the left side, we need to copy them,
		while(leftIndex <= mid) {
			array[left++] = helper[leftIndex++];
		}
		// if still have some elements at the right side, they are already in the right position.
	}
	
	public static void main(String[] args) {
		CoinChange solution = new CoinChange();
		int coinChange = solution.coinChange(new int[] {1,2,5} , 11);
		System.out.println(coinChange);
		
		
	}
}
