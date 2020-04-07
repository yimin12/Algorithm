/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 上午11:00:12
* Description:
* 	You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one 
* 	of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take 
* 	the first turn to remove the stones.
* 	Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you 
* 	can win the game given the number of stones in the heap.
* 	Input: 4
* 	Output: false
* Explanation:
* 	If there are 4 stones in the heap, then you will never win the game;
* 	 No matter 1, 2, or 3 stones you remove, the last stone will always be  removed by your friend
*/

public class NimGame {

	// memory Search
	public boolean firstWillWin(int n) {
		int[] dp = new int[n+1];
		return memorySearch(n, dp);
	}
	public boolean memorySearch(int n, int[] dp) {
		// 0 is empty, 1 is false, 2 is true
		// termination condition
		if(dp[n] != 0) {
			if(dp[n] == 1) {
				return false;
			} else {
				return true;
			}
		}
		// base case
		if(n <= 3) {
			dp[n] = 2;
		} else if(n == 4) {
			dp[n] = 1;
		} else {
			dp[n] = !(memorySearch(n-1, dp) && memorySearch(n-2, dp) && memorySearch(n-3, dp)) ? 2 : 1;
		}
		if(dp[n] == 2) {
			return true;
		}
		return false;
	}
	public boolean canWinNim(int n) {
	     return (n % 4 != 0);
	}
	public static void main(String[] args) {
		NimGame solution = new NimGame();
		boolean firstWillWin = solution.firstWillWin(6);
		System.out.println(firstWillWin);
	}
}
