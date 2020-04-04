/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.Arrays;


import williamsNotebook.easy.tree.maxPathSum;

/**
 * @author yimin Huang
 *	
 *		There are N children standing in a line. Each child is assigned a rating value. 
 *		You are giving candies to these children subjected to the following requirements:
 *		Each child must have at least one candy.
 *		Children with a higher rating get more candies than their neighbors. What is the minimum candies you must give?
 *		
 * Algorithm Class
 */
public class Candy {

	// Solution 1: DP: Time ~ O(N^2), Space ~ O(N) 
	// Let d(i) be the number of candies that gives to child i.
	// There are three cases:
	//  	case 1: if rating[i] > rating[i - 1], ascending problem, dp[i] = dp[i-1]+1
	//		case 2: if rating[i] == rating[i - 1], dp[i] = 1, we do not require equally rating should get least
	// 		case 3: if rating[i] < rating[i - 1],  firstly start with dp[i] = 1, then if previous child has higher rating but equal of less candies than child i
	//				(rating[j-1] > rating[j] && dp[j-1] <= dp[j])
	public int candy(int[] ratings) {
		// Assumption:
		if(ratings == null || ratings.length == 0) return 0;
		int n = ratings.length;
		int[] dp = new int[n];
		dp[0] = 1;
		for(int i = 1; i < n; i++) {
			if(ratings[i-1] == ratings[i]) dp[i] = 1;
			else if(ratings[i-1] < ratings[i]) dp[i] = dp[i-1] + 1;
			else {
				dp[i] = 1;
				int j = i;
				while(j > 0 && ratings[j-1] > ratings[j] && dp[j-1] <= dp[j]) {
					dp[j-1]++;
					j--;
				}
			}
		}
		int total = 0;
		for(int i = 0; i < n; i++) {
			total += dp[i];
		}
		return total;
	}
	// Greedy Solution : Time: O(3N) and Extra Space for O(N) 
	public int candyGreedy(int[] ratings) {
		int n = ratings.length;
		int[] candyNum = new int[n];
		Arrays.fill(candyNum, 1);
		for(int i = 1; i < n; i++) {
			if(ratings[i-1] < ratings[i]) {
				candyNum[i] = candyNum[i-1] + 1;
			}
		}
		for(int i = n - 2; i >= 0; i--) {
			if(ratings[i] > ratings[i+1] && candyNum[i] <= candyNum[i+1]) {
				candyNum[i] = candyNum[i+1] + 1;
			}
		}
		int total = 0;
		for(int i = 0; i < n; i++) {
			total += candyNum[i];
		}
		return total;
	}
	
}
