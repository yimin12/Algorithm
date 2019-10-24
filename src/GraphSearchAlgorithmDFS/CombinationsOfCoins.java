/**
 * 
 */
package GraphSearchAlgorithmDFS;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 上午9:57:14
* Description:
* 	Given a number of different denominations of coins 
* 	(e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to 
* 	pay a target number of cents.
* 
* Arguments
	coins - an array of positive integers representing the different denominations of coins
	 		there are no duplicate numbers and the numbers are sorted by descending order
	 		eg. {25, 10, 5, 2, 1}
	target - a non-negative integer representing the target number of cents, eg. 99
* Assumptions
	coins is not null and is not empty, all the numbers in coins are positive
	target >= 0
	You have infinite number of coins for each of the denominations, 
	you can pick any number of the coins.
* Return
	a list of ways of combinations of coins to sum up to be target.
	each way of combinations is represented by list of integer
	the number at each index means the number of coins used for the denomination at corresponding index.
* Examples
	coins = {2, 1}, target = 4, the return should be
	[ [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)
	  [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)
	  [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)]
* 
*/

public class CombinationsOfCoins {
	public List<List<Integer>> combinations(int target, int[] coins){
//		each combination is represented as List<Integer> cur, and cur.get(i) = the number of coins[i]
//		and all combinations are stored in the result as List of List<Integer>
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helper(target, coins, 0, result, cur);
		return result;
	}
//	target : remaining cents we need to complete
//	coins : all the possible different coins
//	index : we want to see how many coins we need for coins[index]
	private void helper(int target, int[] coins, int index, List<List<Integer>> result, List<Integer> cur) {
//		terminate condition:
//		Notice: this can also be done at index == coins.length where all the coins have been picked
//		but a probably better way is at previous level to reduce number of unnecessary branches in DFS
//		coins.length - 1 represents the last coin we can use and actually what we can do at this level is to 
//		 get target / coins[coins.length - 1] coins if possible
		if(index == coins.length - 1) {
			if(target % coins[coins.length- 1] == 0) {
				cur.add(target/coins[coins.length - 1]);
				result.add(new ArrayList<Integer>(cur));
				cur.remove(cur.size() - 1);
			}
			return;
		}
//		for coins[index], we can pick 0, 1, 2, ... ,target / coins[index] coins
		int max = target / coins[index];
		for(int i = 0; i <= max; i++) {
			cur.add(i);
//			remember to modify the remaining cents for the next level
			helper(target - i * coins[index], coins, index + 1, result, cur);
			cur.remove(cur.size() - 1);
		}
	}
}
