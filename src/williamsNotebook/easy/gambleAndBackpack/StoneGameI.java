/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月4日 下午12:42:03
* Description:
* 	Two players (A and B) are playing a game with stones. Player A always plays first, and the two players move in alternating 
* 	turns. In a single move, a player can remove 2,3,5 stones from the game board. If a player is unable to make a move,
*	that player loses the game;
*/

public class StoneGameI {

	// Dynamic Programming:
	// dp[i] : if there are i coins, would first hand win?
	// Induction rule: dp[i] = !dp[i-2] || !dp[i-3] || !dp[i-5]
	public boolean firstWillWin(int n) {
		if(n < 0) return false;
		boolean[] dp = new boolean[n+1];
		boolean[] flag = new boolean[n+1];
		return search(n, dp, flag);
	}
	private boolean search(int n, boolean[] dp, boolean[] flag) {
		if(flag[n] == true) {
			return dp[n];
		}
		if(n == 0 || n == 1 || n == 4) {
			dp[n] = false;
		} else if(n == 2 || n == 3 || n == 5) {
			dp[n] = true;
		} else {
			dp[n] = !(search(n - 2, dp, flag) && search(n-3, dp, flag) && search(n-5, dp, flag));
		}
		flag[n] = true;
		return dp[n];
	}
	
}
