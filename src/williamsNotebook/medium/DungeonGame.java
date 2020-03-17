/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 *	
 *		The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms 
 *		laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to 
 *		rescue the princess.
 *		The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *		Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) 
 *		or contain magic orbs that increase the knight's health (positive integers).
 *		In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *		Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *		Rules:
 *		1. The knight's health has no upper bound. 
 *		2. Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *	
 * Algorithm Class
 */
public class DungeonGame {

	// dynamic programming . 2-d DP: Time ~ O(M*N), Space ~ O(M*N)
	// dp[i][j] represent that the min hp need to reach [m-1][n-1] from [i][j]
	public int calculateMinimumHP(int[][] dungeon) {
		// Assumption:
		if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
			throw new IllegalArgumentException("The dungeon is invalid");
		}
		int m = dungeon.length;
		int n = dungeon[0].length;
		int[][] dp  = new int[m][n];
		for(int i = m - 1; i >= 0; i--) {
			for(int j = n - 1; j >= 0; j--) {
				// after entering a cell, knight at least have 1 hp
				if(i == m - 1 && j == n - 1) {
					dp[i][j] = Math.max(1, 1 - dungeon[i][j]);
				} else if(i == m - 1) { // only can go right
					dp[i][j] = Math.max(1, dp[i][j+1] - dungeon[i][j]);
				} else if(j == n - 1) { // only can go down
					dp[i][j] = Math.max(1, dp[i+1][j] - dungeon[i][j]);
				} else {
					dp[i][j] = Math.max(Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j], 1);
				}
			}
		}
		return dp[0][0];
	}
	// Optimization by using Rolling array, 
	// 1-d DP: Time ~ O(M*N), Space ~ O(min(M, N));
	public int calculateMinimumHPOptimize(int[][] dungeon) {
		if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
			throw new IllegalArgumentException("The dungeon is invalid");
		}
		int m = dungeon.length;
		int n = dungeon[0].length;
		int min = Math.min(m, n), max = Math.max(m, n);
		int[] d = new int[min];
		int value;
		for(int i = max - 1; i >= 0; i--) {
			for(int j = min - 1; j >= 0; j--) {
				if(n == min - 1) value = dungeon[i][j];
				else value = dungeon[i][j]; // m = min
				if(i == max - 1 && j == min - 1) d[j] = Math.max(1-value, 1);
				else if(i == max - 1) d[j] = Math.max(d[j+1] - value, 1);
				else if(j == min - 1) d[j] = Math.max(d[j] - value, 1);
				else d[j] = Math.max(Math.min(d[j], d[j+1]) - value , 1);
			}
		}
		return d[0];
	}
	public static void main(String[] args) {
		DungeonGame solution = new DungeonGame();
		int[][] matrix = new int[][] {{-2,-3,3},{-5,-10,1},{10,30,-5}};
		int calculateMinimumHP = solution.calculateMinimumHP(matrix);
		System.out.println(calculateMinimumHP);
	}
}
