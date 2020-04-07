/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.HashMap;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午5:54:16
* Description:
* 	In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first 
* 	causes the running total to reach or exceed 100 wins.
* 	What if we change the game so that players cannot re-use integers?
* 	For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100
* 	Given an integermaxChoosableIntegerand another integerdesiredTotal, determine if the first player to move can force a win, assuming 
* 	both players play optimally.	
* 	You can always assume thatmaxChoosableIntegerwill not be larger than 20 anddesiredTotalwill not be larger than 300.
* Example:
* 	Input: maxChooseableinteger = 10, desiredTotal = 11 , Output: false
* Explanation:
* 	No matter which integer the first player choose, the first player will lose.	
* 	The first player can choose an integer from 1 up to 10.
* 	If the first player choose 1, the second player can only choose integers from 2 up to 10.
* 	The second player will win by choosing 10 and get a total = 11, which is  >= desiredTotal. 
* 	Same with other integers chosen by the first player, the second player will always win.
*/
public class CanIWin {

	// Analysis :
	// Most of the "Game Playing" problems on LeetCode can be solved using the top-down DP approach, which 
	// "brute-forcely" simulates every possible state of the game.
	// The key part for the top-down dp strategy is that we need to avoid repeatedly solving sub-problems. 
	// Instead, we should use some strategy to "remember" the outcome of sub-problems. Then when we see them again, we instantly know their result.
	// For this question, the key part is : what is the state of the game?
	// 	1. The unchosen numbers
	// 	2. The remaining desiredTotal to reach
	// A second thought reveals that1)and2)are actually related because we can always get the2)by deducting the sum of chosen numbers from original desiredTotal.
	
	// Time: O(2^n) Space: O(2^n)
	public boolean CanIWin(int maxChoosableInteger, int desiredTotal) {
		int sum = maxChoosableInteger * (maxChoosableInteger + 1)/2;
		// Corner case: if the sum is smaller the request, none of you win
		if(sum < desiredTotal) {
			return false;
		}
		// if the maxChoosableInteger is larger than desiredTotal, you win in the first pick
		if(desiredTotal <= maxChoosableInteger) {
			return false;
		}
		Map<Integer, Boolean> memo = new HashMap<Integer, Boolean>();
		boolean[] used = new boolean[maxChoosableInteger + 1];
		return memorySearch(desiredTotal, memo, used);
	}
	private boolean memorySearch(int desiredTotal, Map<Integer, Boolean> memo, boolean[] used) {
		if(desiredTotal < 0) {
			return false;
		}
		int key = formatKey(used);
		if(!memo.containsKey(key)) {
			for(int  i = 1; i < used.length; i++) {
				if(!used[i]) {
					used[i] = true;
					if(!memorySearch(desiredTotal - 1, memo, used)) {
						memo.put(key, true);
						used[i] = false;
						return true;
					}
					used[i] = false;
				}
			}
			memo.put(key, false);
		}
		return memo.get(key);
	}
	private int formatKey(boolean[] used) {
		int key = 0;
		for(boolean b : used) {
			key = key << 1;
			if(b) {
				key = key | 1;
			}
		}
		return key;
	}
}
